package View;


import Controller.Controller;
import helpers.ConsoleHelper;

public class MainMenuView implements View {
    private Controller controller;
    public void refresh(){
        ConsoleHelper.println("                ***МЕНЮ***                     ");
        ConsoleHelper.println("Для создания журнала                - введите 1");
        ConsoleHelper.println("Для удаления журнала                - введите 2");
        ConsoleHelper.println("Для вставки оценки                  - введите 3");
        ConsoleHelper.println("Для редактирования оценки           - введите 4");
        ConsoleHelper.println("Для удаления оценки                 - введите 5");
        ConsoleHelper.println("Для отображения всех оценок         - введите 6");
        ConsoleHelper.println("Для отображения всех оценок класса  - введите 7");
        ConsoleHelper.println("Для отображения всех оценок журнала - введите 8");
        ConsoleHelper.println("Для отображения всех оценок ученика - введите 9");
        ConsoleHelper.println("Для выхода                     - введите 'exit'");
        controller.mainMenuHandler();
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
