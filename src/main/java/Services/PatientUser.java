package Services;

import javax.persistence.*;

@Entity
public class PatientUser {


    public PatientUser() {

    }

    public PatientUser(String authority, int enabled, int id, String mail, MyPatient mypatient, String phone) {
        this.authority = authority;
        this.enabled = enabled;
        this.id = id;
        Mail = mail;
        this.mypatient = mypatient;
        this.phone = phone;
    }

    //    private  String username ;
//    private String password;
    private  String authority;
    private int enabled ;
    private int id ;
    private String Mail;
    private MyPatient mypatient ;

    private  String phone ;

    @Column(length = 11,updatable = false,nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



//    @Column(unique = true)
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    @Column(nullable = false)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
    @Id
    @GeneratedValue
    @Column(unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    public MyPatient getMypatient() {
        return mypatient;
    }

    public void setMypatient(MyPatient mypatient) {
        this.mypatient = mypatient;
    }

}