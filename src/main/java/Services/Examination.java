package Services;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;

@Entity
@ResponseBody
public class Examination {
    private String date ;
    private int id ;
    private String description;
    private String treatments ;

    public Examination(String treatments, String date, int id, String description) {

        this.treatments = treatments;
        this.date = date;
        this.id = id;
        this.description = description;
    }
     @Column(length = 2000)
    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


     @Id
     @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     @Column(length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Examination() {

    }

    public Examination(String date, int id, String description) {
        this.date = date;
        this.id = id;
        this.description = description;
    }
}
