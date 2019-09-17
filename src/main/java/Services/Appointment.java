package Services;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Appointment {

    private String Time ;
    private String Date ;
    private String patientName ;
    private int Id ;
    private String detail;
    private String status;
    private String disease_name ;
    private String Phone ;

    public Appointment(String time, String date, String patientName, int id, String detail, String status, String disease_name, String phone) {
        Time = time;
        Date = date;
        this.patientName = patientName;
        Id = id;
        this.detail = detail;
        this.status = status;
        this.disease_name = disease_name;
        Phone = phone;
    }

    public Appointment(String time, String date, String patientName, int id, String detail, String status, String disease_name, String phone, Doctor mydoctor) {
        Time = time;
        Date = date;
        this.patientName = patientName;
        Id = id;
        this.detail = detail;
        this.status = status;
        this.disease_name = disease_name;
        Phone = phone;
        Mydoctor = mydoctor;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    @Column(nullable = false)
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }


    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    @Id
    @GeneratedValue
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Appointment() {

    }

    private Doctor Mydoctor = new Doctor();

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER, targetEntity = Doctor.class)
    public Doctor getMydoctor() {
        return Mydoctor;
    }

    public void setMydoctor(Doctor mydoctor) {
        Mydoctor = mydoctor;
    }


}
