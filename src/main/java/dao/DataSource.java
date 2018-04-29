package dao;


import bean.Class;
import bean.Journal;
import bean.ListClass;
import bean.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DataSource {

    private static DataSource ourInstance = new DataSource();
    private ListClass classes;

    public static DataSource getInstance() {
        return ourInstance;
    }

    private DataSource() {
    }

    public void initialization(){

        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван", "Адамс"));
        students.add(new Student("Николай", "Бабушкин"));
        students.add(new Student("Дмитрий", "Вольф"));


        List<Journal> journals = new ArrayList<>();
        journals.add(new Journal("Математика" ,students));
        journals.add(new Journal("Физика" ,students));

        List<Class> classes1 = new ArrayList<>();
        classes1.add(new Class("11Г", students, journals));

        students = new ArrayList<>();

        students.add(new Student("Анатолий", "Васильев"));
        students.add(new Student("Василий", "Максимов"));
        students.add(new Student("Александр", "Морозов"));

        journals = new ArrayList<>();
        journals.add(new Journal("Русский язык" ,students));
        journals.add(new Journal("Литература" ,students));
        journals.add(new Journal("История" ,students));

        classes1.add(new Class("11Д", students, journals));

        classes = new ListClass();
        classes.setClasses(classes1);

        //try {
          //  classes.addEvaluation("11Б", "Русский", "Петя", "Васильев", "5");
        //} catch (Exception e) {
          //  e.printStackTrace();
        //}
    }

    public void saveToXml(String pathToFile){
        try {
            File file = new File(pathToFile);
            JAXBContext context = JAXBContext.newInstance(ListClass.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(classes, file);
        } catch (JAXBException e) {
            System.out.println(e);
        }

    }

    public void loadFromXml(String pathToFile){
        try {
            File file = new File(pathToFile);
            JAXBContext context = JAXBContext.newInstance(ListClass.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            this.classes = (ListClass)unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public ListClass getClasses() {
        return classes;
    }

    public void setClasses(ListClass classes) {
        this.classes = classes;
    }
}
