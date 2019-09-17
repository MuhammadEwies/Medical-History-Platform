package controllers;

import HibernateSecurity.HibernateCRUDPatient;
import Services.MyPatient;
import Services.PatientsImages;
import Services.PatientUser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//well tested
@RestController
public class PatientsController {

//    @RequestMapping(value = "/Patient" ,  method = RequestMethod.DELETE )
//    public String deletePatient( @RequestBody PatientUser user  ) {
//        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
//      return   hiberna.deletePatient(user);
//    }

    @RequestMapping(value = "/Patient" ,  method = RequestMethod.DELETE )
    public String deletePatient( @RequestParam int id ,  @RequestParam String token  ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        return   hiberna.deletePatient(id,token);
    }
    @RequestMapping(value = "/Patient",method = RequestMethod.PUT)
    public boolean updatePatient(@RequestBody MyPatient patient ,@RequestParam String token ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        hiberna.updatePatient(patient,token);
        return true;
    }
    @RequestMapping(value = "/Patient", method = RequestMethod.POST)
    public MyPatient addUser(@RequestBody PatientUser user, @RequestParam String token ) {
        {
            HibernateCRUDPatient hibra = new HibernateCRUDPatient();
            return hibra.AddPatientAndUser(user,token);
        }
    }
    @RequestMapping(value = "/Patients", method = RequestMethod.GET)
    public List<MyPatient> getAllPatients(@RequestParam String token  ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        System.out.println("doctor_id "+id);
        return hiberna.getAllPatients(id);
    }
    @RequestMapping(value = "/PatientsAgesReport", method = RequestMethod.GET)
    public  Map<String,Integer> basedOnAge(@RequestParam String token ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        System.out.println("doctor_id "+id);
        List<MyPatient> myPatients =hiberna.getAllPatients(id);
       Map<String,Integer> report = new HashMap<String, Integer>();
       int under_15 =0;
       int under_25 =0;
       int under_35=0;
       int under_45=0;
       int under_55=0;
       int others=0;
        for(int ie = 0; ie < myPatients.size(); ie++){
           if( myPatients.get(ie).getAge()<=15){
               under_15++;
           }
           else if( myPatients.get(ie).getAge()<=25){
                under_25++;
            }
            else if( myPatients.get(ie).getAge()<=35){
                under_35++;
            }
           else if( myPatients.get(ie).getAge()<=45){
               under_45++;
           }
           else if( myPatients.get(ie).getAge()<=55){
               under_55++;
           }
           else others++;
        }
        report.put("under_15",under_15);
        report.put("under_25",under_25);
        report.put("under_35",under_35);
        report.put("under_45",under_45);
        report.put("under_55",under_55);
        report.put("others",others);
        return report;
    }

//    @RequestMapping(value = "/Exist/{userName}",method = RequestMethod.GET)
//    public boolean exist(@PathVariable(value = "userName") String userName) {
//        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
//        return  hiberna.existUserName(userName);
//    }
    @RequestMapping(value = "/SearchPatients", method = RequestMethod.GET)
    public List<MyPatient> SearchAllPatients(@RequestParam String token ,String phone ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        id= hiberna.getDoctorData(token).getId();
        return hiberna.SearchInPatients(phone , id);
    }
}