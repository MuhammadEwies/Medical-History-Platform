package Services;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SocialMedia {

    private String facebook ;
    private String twitter ;
    private String whatsapp;
    private String linkedin;
    private String youtube ;

    public SocialMedia() {
    }
    @Id
    public String getFacebook() {

        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public SocialMedia(String facebook, String twitter, String whatsapp, String linkedin, String youtube) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.whatsapp = whatsapp;
        this.linkedin = linkedin;
        this.youtube = youtube;
    }
}
