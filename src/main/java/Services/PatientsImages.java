package Services;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PatientsImages {

    public String getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(String uploadDate) {
        UploadDate = uploadDate;
    }

    public PatientsImages() {

    }

    public PatientsImages(String link, String uploadDate) {

        this.link = link;
        UploadDate = uploadDate;
    }

    private String link;
    private String UploadDate;


    @Id
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
