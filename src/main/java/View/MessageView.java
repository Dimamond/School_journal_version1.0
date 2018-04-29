package View;


import helpers.ConsoleHelper;
import model.ModelData;

public class MessageView implements View {
    @Override
    public void refresh(ModelData modelData) {
        ConsoleHelper.println(modelData.getMessage());
    }
}
