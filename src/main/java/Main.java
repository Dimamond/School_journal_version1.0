import Controller.Controller;
import View.*;


import dao.Dao;
import dao.DataSource;
import helpers.ConsoleHelper;
import model.Model;


public class Main {
    public static void main(String[] args) {

        DataSource dataSource = DataSource.getInstance();
        Dao dao = new Dao();
        dao.setDataSource(dataSource);
        dao.loadDataBase(".\\src\\main\\resources\\datasource.xml");
        Controller controller = new Controller();
        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.setController(controller);

        controller.setMainMenuView(mainMenuView);
        controller.setAllClassView(new AllClassView());
        controller.setClassView(new ClassView());
        controller.setJournalView(new JournalView());
        controller.setStudentEvaluationView(new StudentEvaluationView());
        controller.setMessageView(new MessageView());

        Model model = new Model();
        model.setDao(dao);
        controller.setModel(model);

        mainMenuView.refresh();

        dao.saveDataBase(".\\src\\main\\resources\\datasource.xml");
        ConsoleHelper.Close();




    }
}
