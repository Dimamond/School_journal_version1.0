package View;


import bean.Class;
import bean.Evaluation;
import bean.Journal;
import bean.StudentEvaluations;
import helpers.ConsoleHelper;
import model.ModelData;

import java.text.SimpleDateFormat;
import java.util.List;


public class JournalView implements View {

    @Override
    public void refresh(ModelData modelData) {

        Journal journal = modelData.getJournal();
        Class clazz = modelData.getClazz();
        ConsoleHelper.println(String.format("***Журнал по '%s' класса %s***", journal.getSubject(), clazz.getTitle()));
        List<StudentEvaluations> evaluations = journal.getStudentEvaluations();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy/HH:mm:ss");
        for (StudentEvaluations evaluations1 : evaluations){
            ConsoleHelper.println(evaluations1.getStudent().toString());
            List<Evaluation> evaluationList = evaluations1.getEvaluations();
            for (Evaluation evaluation : evaluationList){
                String date = dateFormat.format(evaluation.getDate());
                ConsoleHelper.println(String.format("Оценка %d за %s" , evaluation.getEvaluation(), date));
            }
        }
        ConsoleHelper.println("***КОНЕЦ***");

    }

}
