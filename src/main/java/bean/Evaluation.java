package bean;

import bean.XmlAdapter.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class Evaluation implements Comparable<Evaluation> {

    private Date date;
    private int evaluation;

    public Evaluation() {
    }

    public Evaluation(int evaluation){
        this.evaluation = evaluation;
        date = new Date();

    }

    @Override
    public int compareTo(Evaluation o) {
        return date.compareTo(o.date);
    }

    public Date getDate() {
        return date;
    }

    public int getEvaluation() {
        return evaluation;
    }
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(Date date) {
        this.date = date;
    }
    @XmlElement
    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }
}
