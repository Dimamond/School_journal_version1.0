package View;


import bean.Evaluation;
import bean.StudentEvaluations;
import helpers.ConsoleHelper;
import model.ModelData;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class StudentEvaluationView implements View {

    @Override
    public void refresh(ModelData modelData) {
        List<Map.Entry<String, StudentEvaluations>> studentEvaluations = modelData.getStudentEvaluations();
        ConsoleHelper.println(String.format("***Оценки %s***", modelData.getStudent()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy/HH:mm:ss");
        for (Map.Entry<String, StudentEvaluations> entry: studentEvaluations){
            ConsoleHelper.println(entry.getKey());
            List<Evaluation> evaluations = entry.getValue().getEvaluations();
            for (Evaluation evaluation : evaluations){
                String date = dateFormat.format(evaluation.getDate());
                ConsoleHelper.println(String.format("Оценка %d за %s" , evaluation.getEvaluation(), date));
            }
        }
        ConsoleHelper.println("***КОНЕЦ***");

    }

}
