package model;

import bean.Class;
import bean.Journal;
import bean.ListClass;
import bean.StudentEvaluations;
import dao.Dao;
import exceptions.ArgumentException;
import exceptions.NotFoundException;

import java.util.*;

public class Model {
    private ModelData modelData = new ModelData();
    private Dao dao;

    public void insertJournal(String title, String subject)throws NotFoundException{
        dao.insertJournal(title, subject);
        modelData.setMessage(String.format("Класс %s завел новый журнал по %s", title, subject));
    }

    public void deleteJournal(String title, String subject)throws NotFoundException{
        dao.deleteJournal(title, subject);
        modelData.setMessage(String.format("Класс %s избавился от журнала по %s", title, subject));
    }



    public void insertEvaluation(String title, String subject, String firstName, String lastName, String evaluation)throws NotFoundException, ArgumentException{
        dao.insertEvaluation(title, subject, firstName, lastName, evaluation);
        modelData.setMessage(String.format("Оценка %s поставленна ученику %s %s из класса %s по %s", evaluation, firstName, lastName, title, subject));
    }

    public void updateEvaluation(String title, String subject, String firstName, String lastName, String evaluation, String date)throws NotFoundException, ArgumentException{
        dao.updateEvaluation(title, subject, firstName, lastName, evaluation, date);
        modelData.setMessage(String.format("Оценка %s обновленна у ученика %s %s из класса %s по %s за %s", evaluation, firstName, lastName, title, subject, date));
    }

    public void deleteEvaluation(String title, String subject, String firstName, String lastName, String date)throws NotFoundException, ArgumentException{
        dao.deleteEvaluation(title, subject, firstName, lastName, date);
        modelData.setMessage(String.format("Оценка удаленна у ученика %s %s из класса %s по %s за %s",  firstName, lastName, title, subject, date));
    }

    public void loadAll(){
        ListClass listClass = dao.findAll();
        listClass.sortClasses();
        modelData.setListClass(listClass);
    }

    public void loadClass(String title)throws NotFoundException{
        Class clazz = dao.findByClass(title);
        clazz.sortStudentsAndJournals();
        modelData.setClazz(clazz);
    }

    public void loadJournal(String title, String subject)throws NotFoundException{
        Class clazz = dao.findByClass(title);
        Journal journal = dao.findByJournal(title, subject);
        journal.sortEvaluations();
        modelData.setClazz(clazz);
        modelData.setJournal(journal);
    }


    public void loadStudentEvaluation(String firstName, String lastName)throws NotFoundException{
        modelData.setStudent(dao.findByStudent(firstName, lastName));

        Map<String, StudentEvaluations> map = dao.findByName(firstName, lastName);
        List<Map.Entry<String, StudentEvaluations>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, StudentEvaluations>>() {
            @Override
            public int compare(Map.Entry<String, StudentEvaluations> o1, Map.Entry<String, StudentEvaluations> o2) {
                return o1.getKey().compareToIgnoreCase(o2.getKey());
            }
        });
        for (Map.Entry<String, StudentEvaluations> entry : list)entry.getValue().sortEvaluations();

        modelData.setStudentEvaluations(list);
    }


    public void updateMessage(String message){
        modelData.setMessage(message);
    }

    public ModelData getModelData() {
        return modelData;
    }

    public Dao getDao() {
        return dao;
    }

    public void setModelData(ModelData modelData) {
        this.modelData = modelData;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

}
