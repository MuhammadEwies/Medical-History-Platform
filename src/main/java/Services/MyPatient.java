package Services;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MyPatient {

    private int doc_id;
    private String Name;
    private int Age;
    private String phone;
    private String address;
    private int id;
    private String Mail;
    private List<Examination> myexaminations  = new ArrayList<Examination>();
    private Set<PatientsImages> images = new HashSet<PatientsImages>();
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    public Set<PatientsImages> getImages() {
        return images;
    }

    public void setImages(Set<PatientsImages> images) {
        this.images = images;
    }

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    public List<Examination> getMyexaminations() {
        return myexaminations;
    }
    public void setMyexaminations(List<Examination> myexaminations) {
        this.myexaminations = myexaminations;
    }



    public MyPatient(int doc_id, String name, int age, String phone, String address, int id, String mail) {
        this.doc_id = doc_id;
        Name = name;
        Age = age;
        this.phone = phone;
        this.address = address;
        this.id = id;
        Mail = mail;
    }

    public MyPatient() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
     @Column(updatable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }


}