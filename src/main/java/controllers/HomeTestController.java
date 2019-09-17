package controllers;


import HibernateSecurity.HibernateCRUDPatient;
import HibernateSecurity.NewHIbernateUtile;
import Services.*;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


import java.util.*;
//well tested

@RestController
public class HomeTestController {
//  I wrote this code to test the hash function

//    @RequestMapping(value = "/log", method = RequestMethod.GET)
//    public int loginnew(@RequestParam String username , @RequestParam String password){
//        Session se = NewHIbernateUtile.getSessionFactory().openSession();
//        se.beginTransaction();
//        Query que = se.createQuery("from DoctorUsers where username=:U ");
//        que.setParameter("U",username);
//        List<DoctorUsers>me = new ArrayList<DoctorUsers>();
//        try {
//
//            me = ((org.hibernate.query.Query) que).list();
//            String exactHashedPass = me.get(0).getPassword();
//            if (BCrypt.checkpw(password, exactHashedPass))
//                return me.get(0).getId();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//    @RequestMapping(value = "/checkPass", method = RequestMethod.GET)
//    public Boolean checkpass() {
//
//        String pw_hash = BCrypt.hashpw("mohamed Ewies", BCrypt.gensalt());
//        System.out.println(        BCrypt.gensalt());
//        return BCrypt.checkpw("mohamed Ewies" ,pw_hash);
//    }

    @RequestMapping(value = "/StartApplication", method = RequestMethod.GET)
    public List<MyPatient> Add_Doctor_and_patinet() {

        PatientUser usery = new PatientUser();
        usery.setAuthority("role_admin");
       // usery.setUsername("mohamed");
       // usery.setPassword("mohamed");
        usery.setEnabled(1);
        usery.setPhone("01126330970");
        //BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        //usery.setPassword(pass.encode(usery.getPassword()));
        ///////////////////////////////////////////////////
        MyPatient patiente = new MyPatient();
        patiente.setName("محمد محمود جمعه ");
        patiente.setMail("seifgamal@gmail.com");
        patiente.setPhone("01126330970");

        Examination ex = new Examination();
        ex.setDate("sdfg");
        ex.setDescription("مرض مزمن ف الكبد ");

        PatientsImages me = new PatientsImages();
        me.setLink("asdfg");

        patiente.getImages().add(me);
        patiente.getMyexaminations().add(ex);

        usery.setMypatient(patiente);
        ///////////////////////////////////////////////////
        Doctor doc = new Doctor();
        doc.setName("mohamed");
        Locations myloc = new Locations();
        myloc.setLat("15ll");
        myloc.setLng("sdfg");
        doc.setLocation(myloc);
        ///////////////////////////////////
        //////////////////////////////////////////

        SocialMedia media = new SocialMedia();
        media.setFacebook("jkjgfdsasdfghjknm, bvcfdstyhjbnvfgh");
        doc.setSocialMedia(media);
        /////////////////////
        /////////////////////


        Appointment appoint = new Appointment();
        appoint.setDate("16/8/2019");
        appoint.setTime("6PM");
        appoint.setPhone("011263359870");
        appoint.setDisease_name("sugger ziada");
        appoint.setPatientName("Muhammad Ewies");
        appoint.setStatus("pending");
        //doc.getAppointments().add(appoint);
        appoint.setMydoctor(doc);


        //////////////////////////////////////////
        DoctorUsers user = new DoctorUsers();
        user.setAuthority("role_admin");
        user.setEnabled(1);
        BCrypt.hashpw("user",BCrypt.gensalt());
        user.setPassword(  BCrypt.hashpw("user",BCrypt.gensalt()));
        user.setUsername("user");
        //user.setPassword(pass.encode(user.getPassword()));
        user.setDoc(doc);

        /////////////////////////////////////////
        Session se = new NewHIbernateUtile().getSessionFactory().openSession();
        se.beginTransaction();
        se.save(user);
        se.save(appoint) ;
        se.getTransaction().commit();
        se.close();

        HibernateCRUDPatient hi = new HibernateCRUDPatient();
        patiente.setDoc_id(user.getDoc().getId());
        se = new NewHIbernateUtile().getSessionFactory().openSession();
        se.beginTransaction();
        se.save(usery);
        se.getTransaction().commit();
        se.close();
        return null;
    }

    @RequestMapping(value = "/AddDoctor", method = RequestMethod.POST)
    public boolean addDoctor( @RequestParam String token ,@RequestBody DoctorUsers user ) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        HibernateCRUDPatient hibr = new HibernateCRUDPatient();
       int id = hibr.getIdbyToken(token);
        try {
            Session se = new NewHIbernateUtile().getSessionFactory().openSession();
            se.beginTransaction();
            Doctor doc = new Doctor();
            user.setDoc(doc);
            se.save(user);
            se.getTransaction().commit();
            se.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    @RequestMapping(value = "/ExistUser", method = RequestMethod.GET)
    public boolean ExistUser( @RequestParam String token ,@RequestParam String phone ) {
       HibernateCRUDPatient hibra = new HibernateCRUDPatient();
       return hibra.existPatientPhone(phone);
    }
}