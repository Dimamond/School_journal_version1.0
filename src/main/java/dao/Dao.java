package dao;

import bean.*;
import bean.Class;
import exceptions.ArgumentException;
import exceptions.NotFoundException;


import java.util.Map;

public class Dao {
    private DataSource dataSource;

    public void insertJournal(String title, String subject)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        listClass.addJournal(title, subject);
    }

    public void deleteJournal(String title, String subject)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        listClass.deleteJournal(title, subject);
    }

    public void insertEvaluation(String title, String subject, String firstName, String lastName, String evaluation)throws NotFoundException, ArgumentException {
        ListClass listClass = dataSource.getClasses();
        listClass.addEvaluation(title, subject, firstName, lastName, evaluation);
    }

    public void updateEvaluation(String title, String subject, String firstName, String lastName, String evaluation, String date)throws NotFoundException, ArgumentException{
        ListClass listClass = dataSource.getClasses();
        listClass.updateEvaluation(title, subject, firstName, lastName, evaluation, date);
    }

    public void deleteEvaluation(String title, String subject, String firstName, String lastName, String date)throws NotFoundException, ArgumentException{
        ListClass listClass = dataSource.getClasses();
        listClass.deleteEvaluation(title, subject, firstName, lastName, date);
    }

    public ListClass findAll(){
        return dataSource.getClasses();
    }

    public Class findByClass(String title)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        return listClass.getOneClass(title);
    }

    public Journal findByJournal(String title, String subject)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        return listClass.getJournal(title, subject);
    }


    public Map<String, StudentEvaluations> findByName(String firstName, String lastName)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        return listClass.getStudentEvaluations(firstName, lastName);
    }

    public Student findByStudent(String firstName, String lastName)throws NotFoundException{
        ListClass listClass = dataSource.getClasses();
        return listClass.getStudent(firstName, lastName);
    }


    public void loadDataBase(String pathToFile){
        dataSource.loadFromXml(pathToFile);
    }

    public void saveDataBase(String pathToFile){
        dataSource.saveToXml(pathToFile);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
