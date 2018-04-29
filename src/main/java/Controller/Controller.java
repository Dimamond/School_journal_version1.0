package Controller;


import View.View;
import exceptions.ArgumentException;
import exceptions.NotFoundException;
import helpers.ConsoleHelper;
import model.Model;
import org.apache.logging.log4j.*;



public class Controller {
    private Model model;
    private View mainMenuView;
    private View messageView;
    private View allClassView;
    private View classView;
    private View journalView;
    private View studentEvaluationView;

    private final static Logger LOG = LogManager.getLogger(Controller.class.getName());

    private void journalInsert(){

        while (true){
            String[] s = new String[2];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса в котором хотите создать журнал:");

            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите название предмета по которому хотите создать журнал:");

            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь пытается создать журнал по '%s' в классе %s.", s[1], s[0]));
                model.insertJournal(s[0], s[1]);
                printMessage("Журнал успешно создан.");
                LOG.info(String.format("Пользователь создал журнал по '%s' в классе %s.", s[1], s[0]));
                break;
            } catch (NotFoundException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }

        }
        mainMenuView.refresh();

    }

    private void journalDelete(){
        while (true){
            String[] s = new String[2];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса в котором хотите удалить журнал:");

            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите название предмета по которому хотите удалить журнал:");

            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь пытается удалить журнал по '%s' в классе %s.", s[1], s[0]));
                model.deleteJournal(s[0], s[1]);
                printMessage("Журнал успешно удален.");
                LOG.info(String.format("Пользователь удаляет журнал по '%s' в классе %s.", s[1], s[0]));
                break;
            } catch (NotFoundException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }

        }
        mainMenuView.refresh();

    }

    private void evaluationInsert(){
        while (true){
            String[] s = new String[5];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса:");
            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите имя и фамилию ученика через пробел:");
            String string = ConsoleHelper.readString();
            if(string.trim().equalsIgnoreCase("back"))break;

            String[] temp = string.trim().split(" ");

            s[2] = temp[0];
            s[3] = temp[1];

            printMessage("Введите название предмета:");
            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите оценку которую хотите поставить(от 1 до 5):");
            s[4] = ConsoleHelper.readString();
            if(s[4].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь пытается поставить оценку %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4], s[2], s[3], s[1], s[0]));
                model.insertEvaluation(s[0], s[1], s[2], s[3], s[4]);
                printMessage("Оценка успешно поставленна");
                LOG.info(String.format("Пользователь поставил оценку %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4], s[2], s[3], s[1], s[0]));
                break;
            } catch (NotFoundException | ArgumentException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();

    }



    private void evaluationUpdate(){
        while (true){
            String[] s = new String[6];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса:");
            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите имя и фамилию ученика через пробел:");
            String string = ConsoleHelper.readString();
            if(string.trim().equalsIgnoreCase("back"))break;

            String[] temp = string.trim().split(" ");

            s[2] = temp[0];
            s[3] = temp[1];

            printMessage("Введите название предмета:");
            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите число за которое хотите изменить оценку(dd.MM.yyyy/HH:mm:ss):");
            s[5] = ConsoleHelper.readString();
            if(s[5].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите новую оценку которую хотите поставить(от 1 до 5):");
            s[4] = ConsoleHelper.readString();
            if(s[4].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь пытается обновить оценку %s за %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4], s[5], s[2], s[3], s[1], s[0]));
                model.updateEvaluation(s[0], s[1], s[2], s[3], s[4], s[5]);
                printMessage("Оценка успешно обновленна.");
                LOG.info(String.format("Пользователь обновил оценку %s за %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4], s[5], s[2], s[3], s[1], s[0]));
                break;
            } catch (NotFoundException | ArgumentException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();


    }

    private void evaluationDelete(){
        while (true){
            String[] s = new String[5];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса:");
            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите имя и фамилию ученика через пробел:");
            String string = ConsoleHelper.readString();
            if(string.trim().equalsIgnoreCase("back"))break;

            String[] temp = string.trim().split(" ");

            s[2] = temp[0];
            s[3] = temp[1];

            printMessage("Введите название предмета:");
            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите число за которое хотите удалить оценку(dd.MM.yyyy/HH:mm:ss):");
            s[4] = ConsoleHelper.readString();
            if(s[4].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь пытается удалить оценку за %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4], s[2], s[3], s[1], s[0]));
                model.deleteEvaluation(s[0], s[1], s[2], s[3], s[4]);
                printMessage("Оценка успешно удаленна.");
                LOG.info(String.format("Пользователь  удаляет оценку за %s" +
                        " ученику %s %s, по '%s' в классе %s."  , s[4],  s[2], s[3], s[1], s[0]));
                break;
            } catch (NotFoundException | ArgumentException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();

    }

    private void onShowAllClasses(){
        LOG.info("Пользователь запрашивает вывод на экран всех учеников");
        model.loadAll();
        allClassView.refresh(model.getModelData());
        mainMenuView.refresh();
    }

    private void onShowClass(){
        while (true){
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса:");
            String title = ConsoleHelper.readString();
            if(title.trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь запрашивает вывод на экран ученников класса %s.", title));
                model.loadClass(title);
                classView.refresh(model.getModelData());
                break;
            } catch (NotFoundException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();
    }

    private void onShowJournal(){
        while (true){
            String[] s = new String[2];
            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите назыание класса:");
            s[0] = ConsoleHelper.readString();
            if(s[0].trim().equalsIgnoreCase("back"))break;

            printMessage("Введите назыание предмета:");
            s[1] = ConsoleHelper.readString();
            if(s[1].trim().equalsIgnoreCase("back"))break;

            try {
                LOG.info(String.format("Пользователь запрашивает вывод на экран ученников класса %s по '%s'.", s[0], s[1]));
                model.loadJournal(s[0], s[1]);
                journalView.refresh(model.getModelData());
                break;
            } catch (NotFoundException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();
    }


    private void onShowStudentEvaluations(){
        while (true){

            printMessage("Введите данные или 'back' для возвращения в предыдущее меню.");
            printMessage("Введите имя и фамилию ученика через пробел:");
            String string = ConsoleHelper.readString();
            if(string.trim().equalsIgnoreCase("back"))break;
            String[] s = string.split(" ");
            try {
                LOG.info(String.format("Пользователь запрашивает вывод на экран оценок ученика %s %s.", s[0], s[1]));
                model.loadStudentEvaluation(s[0], s[1]);
                studentEvaluationView.refresh(model.getModelData());
                break;
            } catch (NotFoundException e) {
                printMessage(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
        mainMenuView.refresh();
    }

    public void mainMenuHandler(){
        String string = ConsoleHelper.readString();
        switch (string){
            case "1":
                journalInsert();
                break;
            case "2":
                journalDelete();
                break;
            case "3":
                evaluationInsert();
                break;
            case "4":
                evaluationUpdate();
                break;
            case "5":
                evaluationDelete();
                break;
            case "6":
                onShowAllClasses();
                break;
            case "7":
                onShowClass();
                break;
            case "8":
                onShowJournal();
                break;
            case "9":
                onShowStudentEvaluations();
                break;
            case "exit":
                LOG.info("Пользователь выходит из програмы.");
                break;
            default:
                printMessage("Введена неправильная команда.");
                LOG.info("Пользователь вводит неправильную команду в главном меню.");
                mainMenuView.refresh();
                break;
        }
    }

    private void printMessage(String message){
        model.updateMessage(message);
        messageView.refresh(model.getModelData());
    }

    public Model getModel() {
        return model;
    }

    public View getMainMenuView() {
        return mainMenuView;
    }

    public View getMessageView() {
        return messageView;
    }

    public View getAllClassView() {
        return allClassView;
    }

    public View getClassView() {
        return classView;
    }

    public View getJournalView() {
        return journalView;
    }

    public View getStudentEvaluationView() {
        return studentEvaluationView;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setMainMenuView(View mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    public void setMessageView(View messageView) {
        this.messageView = messageView;
    }

    public void setAllClassView(View allClassView) {
        this.allClassView = allClassView;
    }

    public void setClassView(View classView) {
        this.classView = classView;
    }

    public void setJournalView(View journalView) {
        this.journalView = journalView;
    }

    public void setStudentEvaluationView(View studentEvaluationView) {
        this.studentEvaluationView = studentEvaluationView;
    }
}
