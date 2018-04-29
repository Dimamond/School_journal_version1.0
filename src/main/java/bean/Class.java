package bean;

import exceptions.ArgumentException;
import exceptions.NotFoundException;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

public class Class implements Comparable<Class> {
    private String title;
    private List<Student> students;
    private List<Journal> journals;

    public Class() {
    }

    public Class(String title, List<Student> students, List<Journal> journals) {
        this.title = title;
        this.students = students;
        this.journals = journals;
    }

    public void addEvaluation(String subject, String firstName, String lastName, String evaluation)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < journals.size(); i++){
            if(journals.get(i).getSubject().equalsIgnoreCase(subject.trim())){
                journals.get(i).addEvaluation(firstName, lastName, evaluation);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Предмет %s не найдет", subject));
    }

    public void updateEvaluation(String subject ,String firstName, String lastName, String evaluation, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < journals.size(); i++){
            if(journals.get(i).getSubject().equalsIgnoreCase(subject.trim())){
                journals.get(i).updateEvaluation(firstName, lastName, evaluation, date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Предмет %s не найдет", subject));
    }

    public void deleteEvaluation(String subject ,String firstName, String lastName, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < journals.size(); i++){
            if(journals.get(i).getSubject().equalsIgnoreCase(subject.trim())){
                journals.get(i).deleteEvaluation(firstName, lastName, date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("Предмет %s не найдет", subject));
    }

    public Map<String ,StudentEvaluations> getStudentEvaluations(String firstName, String lastName)throws NotFoundException{
        Map<String,StudentEvaluations> map = new HashMap<>();
        for (int i = 0; i < journals.size(); i++){
            try {
                StudentEvaluations evaluations = journals.get(i).getStudentEvaluation(firstName, lastName);
                map.put(journals.get(i).getSubject(), evaluations);
            } catch (NotFoundException e) {

            }

        }
        if(map.size() == 0)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));
        return map;
    }

    public Journal getJournal(String title)throws NotFoundException{
        Journal journal = null;
        for (int i = 0; i < journals.size(); i++){
            if(journals.get(i).getSubject().equalsIgnoreCase(title.trim())){
                journal = journals.get(i);
                break;
            }
        }
        if(journal == null)throw new NotFoundException(String.format("Журнал по %s не найден ", title));
        return journal;
    }

    public void addJournal(String title){
        journals.add(new Journal(title.trim(), students));
    }

    public void deleteJournal(String title)throws NotFoundException{
        boolean isFound = false;
        for (int i = 0; i < journals.size(); i++){
            if(journals.get(i).getSubject().equalsIgnoreCase(title.trim())){
                journals.remove(i);
                isFound = true;
                break;

            }
        }
        if(!isFound)throw new NotFoundException(String.format("Журнал по %s не найден", title));
    }

    public Student getStudent(String firtsName, String lastName)throws NotFoundException{
        Student student = null;
        for (int i = 0; i < students.size(); i++){
            if(students.get(i).getFirstName().equalsIgnoreCase(firtsName.trim()) && students.get(i).getLastName().equalsIgnoreCase(lastName.trim())){
                student = students.get(i);
                break;

            }
        }
        if(student == null)throw new NotFoundException(String.format("ученик %s %s не найден", firtsName, lastName));
        return student;
    }

    public void sortStudentsAndJournals(){
        Collections.sort(students);
        Collections.sort(journals);
    }

    @Override
    public int compareTo(Class o) {
        return title.compareToIgnoreCase(o.getTitle());
    }

    public String getTitle() {
        return title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Journal> getJournals() {
        return journals;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    @XmlElement(name = "journal")
    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
