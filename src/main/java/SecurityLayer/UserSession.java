package SecurityLayer;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class UserSession {

    private String token ;
    private int UserID;
    private LocalDateTime last ;
    @Id
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserID() {
        return UserID;
    }

        public void setUserID(int userID) {
        UserID = userID;
    }


    public LocalDateTime getLast() {
        return last;
    }

    public void setLast(LocalDateTime last) {
        this.last = last;
    }

    public UserSession(String token, int userID, LocalDateTime last) {

        this.token = token;
        UserID = userID;
        this.last = last;
    }

    public UserSession() {

    }
}
