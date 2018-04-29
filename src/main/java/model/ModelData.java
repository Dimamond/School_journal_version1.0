package model;

import bean.*;
import bean.Class;

import java.util.List;
import java.util.Map;

public class ModelData {
    private ListClass listClass;
    private Class clazz;
    private Journal journal;
    private List<Map.Entry<String, StudentEvaluations>> studentEvaluations;
    private Student student;
    private String message;

    public void setListClass(ListClass listClass) {
        this.listClass = listClass;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public void setStudentEvaluations(List<Map.Entry<String, StudentEvaluations>> studentEvaluations) {
        this.studentEvaluations = studentEvaluations;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListClass getListClass() {
        return listClass;
    }

    public Class getClazz() {
        return clazz;
    }

    public Journal getJournal() {
        return journal;
    }

    public List<Map.Entry<String, StudentEvaluations>> getStudentEvaluations() {
        return studentEvaluations;
    }

    public Student getStudent() {
        return student;
    }

    public String getMessage() {
        return message;
    }
}
