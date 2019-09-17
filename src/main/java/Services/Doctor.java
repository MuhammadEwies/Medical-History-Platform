 package Services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

 @Entity
 public class Doctor  {

     private PatientsImages profilepicture ;
     private Locations location ;
     private SocialMedia socialMedia;
     private String name;
     private String department ;
     private String city ;
     private String bio ;
     private String image ;
     private String address;
     private String First_phone ;
     private String Second_phone;
     private String Open_dates;
     private String Mail ;
     private int id ;

     public Doctor(PatientsImages profilepicture, Locations location, SocialMedia socialMedia, String name, String department, String city, String bio, String image, String address, String first_phone, String second_phone, String open_dates, String mail, int id, List<Appointment> appointments) {
         this.profilepicture = profilepicture;
         this.location = location;
         this.socialMedia = socialMedia;
         this.name = name;
         this.department = department;
         this.city = city;
         this.bio = bio;
         this.image = image;
         this.address = address;
         First_phone = first_phone;
         Second_phone = second_phone;
         Open_dates = open_dates;
         Mail = mail;
         this.id = id;
         Appointments = appointments;
     }
     @OneToOne(cascade=CascadeType.ALL,fetch =FetchType.EAGER)
     public PatientsImages getProfilepicture() {

         return profilepicture;
     }

     public void setProfilepicture(PatientsImages profilepicture) {
         this.profilepicture = profilepicture;
     }

     @OneToOne(cascade = CascadeType.ALL,targetEntity = SocialMedia.class,fetch = FetchType.EAGER)
     public SocialMedia getSocialMedia() {
         return socialMedia;
     }

     public void setSocialMedia(SocialMedia socialMedia) {
         this.socialMedia = socialMedia;
     }

     @OneToOne(fetch = FetchType.EAGER , targetEntity = Locations.class,cascade = CascadeType.ALL)
     public Locations getLocation() {
         return location;
     }

     public void setLocation(Locations location) {
         this.location = location;
     }

     public Doctor(Locations location, String name, String department, String city, String bio, String image, String address, String first_phone, String second_phone, String open_dates, String mail, int id) {
         this.location = location;
         this.name = name;
         this.department = department;
         this.city = city;
         this.bio = bio;
         this.image = image;
         this.address = address;
         First_phone = first_phone;
         Second_phone = second_phone;
         Open_dates = open_dates;
         Mail = mail;
         this.id = id;
     }

     public String getDepartment() {

         return department;
     }

     public void setDepartment(String department) {
         this.department = department;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }


     @Column(length = 10000)
     public String getBio() {
         return bio;
     }

     public void setBio(String bio) {
         this.bio = bio;
     }

     public String getImage() {
         return image;
     }

     public void setImage(String image) {
         this.image = image;
     }



     public Doctor() {
     }
     public Doctor(String name, String address, String first_phone, String second_phone, String open_dates, String mail, int id) {
         this.name = name;
         this.address = address;
         First_phone = first_phone;
         Second_phone = second_phone;
         Open_dates = open_dates;
         Mail = mail;
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getFirst_phone() {
         return First_phone;
     }

     public void setFirst_phone(String first_phone) {
         First_phone = first_phone;
     }

     public String getSecond_phone() {
         return Second_phone;
     }

     public void setSecond_phone(String second_phone) {
         Second_phone = second_phone;
     }

     public String getOpen_dates() {
         return Open_dates;
     }

     public void setOpen_dates(String open_dates) {
         Open_dates = open_dates;
     }

     public String getMail() {
         return Mail;
     }

     public void setMail(String mail) {
         Mail = mail;
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


     public Doctor(Locations location, SocialMedia socialMedia, String name, String department, String city, String bio, String image, String address, String first_phone, String second_phone, String open_dates, String mail, int id, List<Appointment> appointments) {
         this.location = location;
         this.socialMedia = socialMedia;
         this.name = name;
         this.department = department;
         this.city = city;
         this.bio = bio;
         this.image = image;
         this.address = address;
         First_phone = first_phone;
         Second_phone = second_phone;
         Open_dates = open_dates;
         Mail = mail;
         this.id = id;
         Appointments = appointments;
     }


//     private Set<Appointment> Appointments = new HashSet <Appointment>();
//
//     @JsonIgnore
//     @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
//     public Set<Appointment> getAppointments() {
//         return Appointments;
//     }
//
//     public void setAppointments(Set<Appointment> Appointments) {
//         this.Appointments = Appointments;
//     }
//

     private List<Appointment> Appointments = new ArrayList<Appointment>();

     
     @JsonIgnore
     @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER, targetEntity = Appointment.class)
     public List<Appointment> getAppointments() {
         return Appointments;
     }

     public void setAppointments(List<Appointment> appointments) {
         Appointments = appointments;
     }
 }
