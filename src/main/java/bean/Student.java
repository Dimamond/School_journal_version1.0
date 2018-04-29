package bean;

import javax.xml.bind.annotation.XmlElement;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Student o) {
        int result = lastName.compareToIgnoreCase(o.getLastName());
        if(result != 0) return result;
        return firstName.compareToIgnoreCase(o.getFirstName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
