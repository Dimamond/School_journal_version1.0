package bean;

import exceptions.ArgumentException;
import exceptions.NotFoundException;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Journal implements Comparable<Journal> {
    private String subject;
    private List<StudentEvaluations> studentEvaluations;

    public Journal() {
    }

    public Journal(String subject, List<Student> students) {
        this.subject = subject;
        this.studentEvaluations = new ArrayList<>();
        for (Student student : students){
            StudentEvaluations newStudentEvaluations = new StudentEvaluations(student);
            studentEvaluations.add(newStudentEvaluations);
        }

    }

    public void addEvaluation(String firstName, String lastName, String evaluation)throws ArgumentException, NotFoundException{
        boolean isFound = false;
        for (int i = 0; i < studentEvaluations.size(); i++){
            Student student = studentEvaluations.get(i).getStudent();
            if(student.getFirstName().equalsIgnoreCase(firstName.trim()) && student.getLastName().equalsIgnoreCase(lastName.trim())){
                studentEvaluations.get(i).addEvaluation(evaluation);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));

    }

    public void updateEvaluation(String firstName, String lastName, String evaluation, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;
        for (int i = 0; i < studentEvaluations.size(); i++){
            Student student = studentEvaluations.get(i).getStudent();
            if(student.getFirstName().equalsIgnoreCase(firstName.trim()) && student.getLastName().equalsIgnoreCase(lastName.trim())){
                studentEvaluations.get(i).updateEvaluation(evaluation, date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));
    }

    public void deleteEvaluation(String firstName, String lastName, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;
        for (int i = 0; i < studentEvaluations.size(); i++){
            Student student = studentEvaluations.get(i).getStudent();
            if(student.getFirstName().equalsIgnoreCase(firstName.trim()) && student.getLastName().equalsIgnoreCase(lastName.trim())){
                studentEvaluations.get(i).deleteEvaluation(date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));
    }

    public StudentEvaluations getStudentEvaluation(String firstName, String lastName)throws NotFoundException{

        StudentEvaluations studentEvaluations = null;
        for (int i = 0; i < this.studentEvaluations.size(); i++){
            Student student = this.studentEvaluations.get(i).getStudent();
            if(student.getFirstName().equalsIgnoreCase(firstName.trim()) && student.getLastName().equalsIgnoreCase(lastName.trim())){
                studentEvaluations = this.studentEvaluations.get(i);
                break;
            }
        }
        if(studentEvaluations == null)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));
        return studentEvaluations;
    }

    public void sortEvaluations(){
        for (StudentEvaluations evaluations : studentEvaluations){
            evaluations.sortEvaluations();
        }
        Collections.sort(studentEvaluations);
    }

    @Override
    public int compareTo(Journal o) {
        return subject.compareToIgnoreCase(o.getSubject());
    }



    @XmlElement
    public void setSubject(String subject) {
        this.subject = subject;
    }
    @XmlElement
    public void setStudentEvaluations(List<StudentEvaluations> studentEvaluations) {
        this.studentEvaluations = studentEvaluations;
    }

    public String getSubject() {
        return subject;
    }

    public List<StudentEvaluations> getStudentEvaluations() {
        return studentEvaluations;
    }
}
