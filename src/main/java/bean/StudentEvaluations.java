package bean;

import com.sun.javaws.exceptions.InvalidArgumentException;
import exceptions.ArgumentException;
import exceptions.NotFoundException;

import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StudentEvaluations implements Comparable<StudentEvaluations> {

    private Student student;
    private List<Evaluation> evaluations;

    public StudentEvaluations() {
        this.evaluations = new ArrayList<>();
    }

    public StudentEvaluations(Student student) {
        this.student = student;
        this.evaluations = new ArrayList<>();
    }

    public void addEvaluation(String evaluation)throws ArgumentException{
        byte[] bytes = evaluation.getBytes();
        if((bytes.length > 1) || (bytes[0] < 48 || bytes[0] > 53))throw new ArgumentException ("Оценка должна быть от 1-го до 5-ти включительно");

        evaluations.add(new Evaluation(bytes[0] - 48));
    }

    public void updateEvaluation(String newEvaluation, String stringDate)throws NotFoundException, ArgumentException{
        byte[] bytes = newEvaluation.getBytes();
        if((bytes.length > 1) || (bytes[0] < 48 || bytes[0] > 53))throw new ArgumentException ("Оценка должна быть от 1-го до 5-ти включительно");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy/HH:mm:ss");

        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate.trim());
        } catch (ParseException e) {
            throw new ArgumentException("Дата введена не по образцу");
        }
        boolean isFound = false;
        for (Evaluation evaluation : evaluations){
            if(evaluation.getDate().equals(date)){
                evaluation.setEvaluation(bytes[0] - 48);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException("Оценка по данной дате не найдена");
    }

    public void deleteEvaluation(String stringDate)throws NotFoundException, ArgumentException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy/HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate.trim());
        } catch (ParseException e) {
            throw new ArgumentException("Дата введена не по образцу");
        }
        boolean isFound = false;
        for (int i = 0; i < evaluations.size(); i++){
            if(evaluations.get(i).getDate().equals(date)){
                isFound = true;
                evaluations.remove(i);
                break;
            }
        }
        if(!isFound)throw new NotFoundException("Оценка по данной дате не найдена");

    }

    public void sortEvaluations(){
        Collections.sort(evaluations);
    }

    @Override
    public int compareTo(StudentEvaluations o) {
        int result = student.getLastName().compareToIgnoreCase(o.getStudent().getLastName());
        if(result != 0) return result;
        return student.getFirstName().compareToIgnoreCase(o.getStudent().getFirstName());
    }

    public Student getStudent() {
        return student;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
    @XmlElement
    public void setStudent(Student student) {
        this.student = student;
    }
    @XmlElement
    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
