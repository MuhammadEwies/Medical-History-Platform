package controllers;

import HibernateSecurity.HibernateCRUDPatient;
import Services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@ResponseBody
@RestController
public class AppointmentController {
    @RequestMapping(value = "/Appointments" , method = RequestMethod.GET)
    public List<Appointment> getAppointments(@RequestParam String token ){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);

        return  hiberna.getAllAppointments(id);
    }
    @RequestMapping(value = "/Appointment" , method = RequestMethod.POST)
    public boolean addAppointment(@RequestParam String token , @RequestBody Appointment appointment){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        return  hiberna.addAppointment(id , appointment);
    }
    @RequestMapping(value = "/Appointment" , method = RequestMethod.PUT)
    public boolean updateAppointments(@RequestParam String token ,@RequestBody Appointment appointment){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        return  hiberna.updateAppointment(id ,appointment);
    }
    @RequestMapping(value = "/AppointmentsReport" , method = RequestMethod.GET)
    public AppointmentsRate updateAppointments(@RequestParam String token ){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        return  hiberna.getRate(id);
    }
    @RequestMapping(value = "/Appointment" , method = RequestMethod.DELETE)
    public boolean deleteAppointment(@RequestParam String token , @RequestBody Appointment appointment){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        System.out.println(id);
        return  hiberna.DeleteAppointment(id , appointment);
    }
    @RequestMapping(value = "/PendingAppointments" , method = RequestMethod.GET)
    public List<Appointment> getPendingAppointments(@RequestParam String token ){
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);

        return  hiberna.getAllPendingAppointments(id);
    }

    @RequestMapping(value = "/AppointmentToUser" , method = RequestMethod.POST)
    public MyPatient ConvertToUser(@RequestParam String token , @RequestBody Appointment appointment) {
        PatientUser Converted_User = new PatientUser();
        Converted_User.setPhone(appointment.getPhone());
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        int id = hiberna.getIdbyToken(token);
        appointment.setStatus("Closed Won");
        hiberna.updateAppointment(id ,appointment );
        return hiberna.AddPatientAndUser(Converted_User, token);
    }
}
