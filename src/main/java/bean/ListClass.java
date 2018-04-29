package bean;


import exceptions.ArgumentException;
import exceptions.NotFoundException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class ListClass {

    private List<Class> classes = new ArrayList<>();

    public ListClass() {

    }

    public void addEvaluation(String title, String subject, String firstName, String lastName, String evaluation)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                classes.get(i).addEvaluation(subject, firstName, lastName, evaluation);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("%s не найден", title));
    }

    public void updateEvaluation(String title, String subject, String firstName, String lastName, String evaluation, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                classes.get(i).updateEvaluation(subject, firstName, lastName, evaluation, date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("%s не найден", title));
    }

    public void deleteEvaluation(String title, String subject, String firstName, String lastName, String date)throws NotFoundException, ArgumentException{
        boolean isFound = false;

        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                classes.get(i).deleteEvaluation(subject, firstName, lastName, date);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("%s не найден", title));
    }

    public void addJournal(String title, String subject)throws NotFoundException{
        boolean isFound = false;

        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                classes.get(i).addJournal(subject.trim());
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("%s не найден", title));
    }

    public void deleteJournal(String title, String subject)throws NotFoundException{
        boolean isFound = false;
        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                classes.get(i).deleteJournal(subject);
                isFound = true;
                break;
            }
        }
        if(!isFound)throw new NotFoundException(String.format("%s не найден", title));
    }

    public Map<String, StudentEvaluations> getStudentEvaluations(String firstName, String lastName)throws NotFoundException{
        Map<String ,StudentEvaluations> map = null;
        for (int i = 0; i < classes.size(); i++){
            try {
                map = classes.get(i).getStudentEvaluations(firstName, lastName);
                break;
            } catch (NotFoundException e) {

            }

        }
        if(map == null)throw new NotFoundException(String.format("Ученик %s %s не найден", firstName, lastName));
        return map;
    }

    public Journal getJournal(String title, String subject)throws NotFoundException{
        Journal journal = null;
        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                journal = classes.get(i).getJournal(subject);
                break;
            }
        }
        if(journal == null)throw new NotFoundException(String.format("Журнал по %s не найден", title));
        return journal;
    }

    public Class getOneClass(String title)throws NotFoundException{

        Class clazz = null;
        for (int i = 0; i < classes.size(); i++){
            if(classes.get(i).getTitle().equalsIgnoreCase(title.trim())){
                clazz = classes.get(i);
                break;
            }
        }
        if(clazz == null)throw new NotFoundException(String.format("класс %s не найден", title));
        return clazz;
    }

    public Student getStudent(String firtsName, String lastName)throws NotFoundException{
        Student student = null;
        for (int i = 0; i < classes.size(); i++){
            try {
                student = classes.get(i).getStudent(firtsName, lastName);
                break;
            } catch (NotFoundException e) {

            }
        }
        if(student == null)throw new NotFoundException(String.format("ученик %s %s не найден", firtsName, lastName));
        return student;
    }

    public void sortClasses(){
        for (Class clazz : classes) clazz.sortStudentsAndJournals();
        Collections.sort(classes);
    }




    public boolean addClass(Class clazz){
        return this.classes.add(clazz);
    }

    public boolean deleteClass(Class clazz){
        return this.classes.remove(clazz);
    }

    public List<Class> getClasses() {
        return classes;
    }


    @XmlElement(name="class")
    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
