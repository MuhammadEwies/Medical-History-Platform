    package HibernateSecurity;

import SecurityLayer.UserSession;
import Services.*;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Query;

import java.util.*;

    public class HibernateCRUDPatient {

    public Doctor getDoctorData(String token){
       int id = getIdbyToken(token);

        Doctor doc;
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        doc = se.get(DoctorUsers.class , id).getDoc();
        se.getTransaction().commit();
        return doc;
    }
    public String deletePatient(PatientUser user) {

        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        user.setEnabled(0);
        se.update(user);
        se.getTransaction().commit();
        return "deactivated Successfully";
    }
    public boolean updatePatient(MyPatient patiente,String token) {
            Session see = NewHIbernateUtile.getSessionFactory().openSession();
            see.beginTransaction();
            int id = getIdbyToken(token);
            id =see.get(DoctorUsers.class,id).getDoc().getId();
            patiente.setDoc_id(id);
            see.update(patiente);
            see.flush();
            //see.getTransaction().commit();
            see.close();
            return true;
    }
//    public boolean existUserName(String userName) {
//        Session se = NewHIbernateUtile.getSessionFactory().openSession();
//        se.beginTransaction();
//        Query qe = (Query) se.createQuery("from PatientUser where username = :Us ");
//        qe.setParameter("Us", userName);
//        List<PatientUser> myusers = (List<PatientUser>) ((org.hibernate.query.Query) qe).list();
//        se.getTransaction().commit();
//        if ((myusers.size() == 1) && (myusers.get(0).getUsername().equals(userName))) {
//            return true;
//        } else
//            return false;
//    }
//    public MyPatient AddPatientAndUser(PatientUser user, String token) {
//        if (user.getPassword() == null) {
//            return null;
//        } else {
//            user.setEnabled(1);
//            user.setAuthority("ROLE_USER");
//           // BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
//           // user.setPassword(pass.encode(user.getPassword()));
//            /////////////////////////////////////
//            Session se = NewHIbernateUtile.getSessionFactory().openSession();
//            se.beginTransaction();
//            int id = getIdbyToken(token);
//            id =se.get(DoctorUsers.class,id).getDoc().getId();
//            System.out.println(id);
//            MyPatient pa =new MyPatient();
//            pa.setDoc_id(id);
//            user.setMypatient(pa);
//            ///////////////////////////////////////
//
//            se.save(user);
//            se.getTransaction().commit();
//            return user.getMypatient();
//        }
//    }
public MyPatient AddPatientAndUser(PatientUser user, String token) {
        try {
            user.setEnabled(1);
            user.setAuthority("ROLE_USER");

            /////////////////////////////////////
            if(!user.getPhone().equals(null)) {
                Session se = NewHIbernateUtile.getSessionFactory().openSession();
                se.beginTransaction();
                int id = getIdbyToken(token);
                id = se.get(DoctorUsers.class, id).getDoc().getId();
                System.out.println(id);
                MyPatient pa = new MyPatient();
                pa.setPhone(user.getPhone());
                pa.setDoc_id(id);
                user.setMypatient(pa);
                ///////////////////////////////////////
                se.save(user);
                se.getTransaction().commit();

                return user.getMypatient();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
}

        public boolean existPatientPhone(String phone) {
            Session see = NewHIbernateUtile.getSessionFactory().openSession();
            see.beginTransaction();
            Query query = see.createQuery("from PatientUser where phone=:PO");
            query.setParameter("PO",phone);
            if(((org.hibernate.query.Query) query).list().size()>0){
                see.close();
                return true;
            }
            else return false;
        }
    public List<MyPatient> getAllPatients(int id) {

        Session see = NewHIbernateUtile.getSessionFactory().openSession();
        see.beginTransaction();
        int doctor_id= see.get(DoctorUsers.class,id).getDoc().getId();
        Query qe =see.createQuery("from MyPatient where doc_id =:Id");
        qe.setParameter("Id", doctor_id);
        List<MyPatient> myallPa = ((org.hibernate.query.Query) qe).list();
        see.getTransaction().commit();
        see.close();

        return myallPa;
    }
    public DoctorUsers GetUserbyMail(String Mail) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        Query qe = se.createQuery(" from DoctorUsers where mail= :Us");
        qe.setParameter("Us", Mail);
        List<DoctorUsers> me = ((org.hibernate.query.Query) qe).list();
        if ((me.size() == 1) && (me.get(0).getMail().equals(Mail))) {
            return me.get(0);
        }
        se.getTransaction().commit();
        return null;
    }
    public boolean UpdateDoctorData(Doctor doc,String token) {
        HibernateCRUDPatient CRud = new HibernateCRUDPatient();
        int doctor_id = CRud.getDoctorData(token).getId();
         doc.setAppointments(CRud.getDoctorData(token).getAppointments());

        if(doctor_id== doc.getId()) {
                Session se = NewHIbernateUtile.getSessionFactory().openSession();
                se.beginTransaction();
                se.update(doc);
                se.getTransaction().commit();
                se.close();
            return true;
        }
        else     return false;
    }
    public int login(String username , String password){
        /* Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        Query que = se.createQuery("select id  from DoctorUsers where username=:U and password =:P");
        que.setParameter("U",username);
        que.setParameter("P",password);
        List<Integer> me = new ArrayList<Integer>();
        me= ((org.hibernate.query.Query) que).list();

        System.out.println("array login id " +me);
        if(me.size()==1){
            int id_doc =se.get(DoctorUsers.class,me.get(0)).getDoc().getId();
            se.getTransaction().commit();
            return id_doc;

        }
        else return 0;
        */

        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        Query que = se.createQuery("from DoctorUsers where username=:U ");
        que.setParameter("U",username);
        List<DoctorUsers>me = new ArrayList<DoctorUsers>();

        try {

            me = ((org.hibernate.query.Query) que).list();
            String exactHashedPass = me.get(0).getPassword();
            if (BCrypt.checkpw(password, exactHashedPass))
                return me.get(0).getId();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;

    }

    public boolean checktoken(String token) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();

        if(token ==null){
            System.out.println("token null");
          return  false;
        }
        UserSession sessions =null;
            try {
                   se.beginTransaction();
                sessions = (UserSession) se.get(UserSession.class, token);
                se.close();
                if(sessions==null) {
                    System.out.println("token not fount");
                    return false;
                }
                else {
                    //if(sessions.getLast().plusMinutes(30).isBefore(LocalDateTime.now()))
                    return true;
                }
            }

            catch (Exception e)
            {
                return false;
            }
    }
    public boolean saveSession(UserSession userSession) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        se.save(userSession);
        se.getTransaction().commit();
        se.close();

        return true;
    }
    public boolean deleteSession(String token) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        UserSession userSession=new UserSession();
         userSession = (UserSession) se.get(UserSession.class, token);
        se.delete(userSession);
        se.getTransaction().commit();
        se.close();

        return true;
    }
    public int getIdbyToken(String token) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        UserSession userSession=new UserSession();
         userSession = (UserSession) se.get(UserSession.class, token);
        se.getTransaction().commit();
        se.close();

        return userSession.getUserID() ;
    }

    public List<PatientsImages> getAllImages(int id) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        List<PatientsImages> images=new ArrayList<PatientsImages>();
        images = (List<PatientsImages>) se.get(MyPatient.class, id).getImages();
        se.getTransaction().commit();
        se.close();

        return images ;
    }

    public List<Appointment> getAllAppointments(int id) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        List<Appointment> Appointments=new ArrayList<Appointment>();
         id =  se.get(DoctorUsers.class,id).getDoc().getId();

        //Appointments  =  se.get(Doctor.class,id).getAppointments();
        Query query = se.createQuery(" from Appointment where mydoctor_id = :SE");
        query.setParameter("SE",id);
        Appointments = ((org.hibernate.query.Query) query).list();
        se.getTransaction().commit();
        se.close();
        return Appointments;
    }

    public boolean addAppointment(int id , Appointment appointment) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        Doctor doc =se.get(DoctorUsers.class,id).getDoc();
        appointment.setMydoctor(doc);
        se.save(appointment);
        se.getTransaction().commit();
        se.close();
        return true;
    }

    public boolean updateAppointment(int id , Appointment appointment) {
        Session se = NewHIbernateUtile.getSessionFactory().openSession();
        se.beginTransaction();
        Doctor doc =se.get(DoctorUsers.class,id).getDoc();
        appointment.setMydoctor(doc);
        se.update(appointment);
        se.getTransaction().commit();
        se.close();
         return true;
    }

    public AppointmentsRate getRate(int id) {

            AppointmentsRate doctorRate =new AppointmentsRate();
            Session se = NewHIbernateUtile.getSessionFactory().openSession();
            se.beginTransaction();
            List<Appointment> Appointments=new ArrayList<Appointment>();

            id = se.get(DoctorUsers.class,id).getDoc().getId();
            Appointments =  se.get(Doctor.class,id).getAppointments();
            System.out.println(Appointments.get(0).getDisease_name());
            se.getTransaction().commit();
            Appointment app = new Appointment();
            for(int i=0;i<Appointments.size();i++){
                app = Appointments.get(0);
               if( app.getStatus().equals("Fail"))
                   doctorRate.setFail(doctorRate.getFail()+1);
               else if(app.getStatus().equals("Success"))
                   doctorRate.setSuccess(doctorRate.getSuccess()+1);
               else if (app.getStatus().equals("Pending"))
                   doctorRate.setPending(doctorRate.getPending()+1);
               else
                   doctorRate.setPending(doctorRate.getPending()+1);
            }

            return doctorRate;
        }

        public String deletePatient(int id,String token) {

            Session se = NewHIbernateUtile.getSessionFactory().openSession();
            se.beginTransaction();
            int Docid = getIdbyToken(token);
            MyPatient patient =se.get(MyPatient.class,id);
            if(patient.getDoc_id()==Docid){
                se.delete(patient);
                se.getTransaction().commit();
                se.close();
                return "deleted Successfully";
            }
            else
            {
                se.close();
                return "Can not be deleted";
            }
        }

        public boolean DeleteAppointment(int id, Appointment appointment) {
            Session se = NewHIbernateUtile.getSessionFactory().openSession();
            se.beginTransaction();
            Appointment appointment1= se.find(Appointment.class,appointment.getId());
            se.delete(appointment1);
            se.getTransaction().commit();
            se.close();
            return true;
        }

        public List<Appointment> getAllPendingAppointments(int id) {
            try {
                Session se = NewHIbernateUtile.getSessionFactory().openSession();
                se.beginTransaction();
                List<Appointment> Appointments=new ArrayList<Appointment>();
                id =  se.get(DoctorUsers.class,id).getDoc().getId();
                Query query = se.createQuery(" from Appointment where mydoctor_id = :SE");
                query.setParameter("SE",id);
                Appointments = ((org.hibernate.query.Query) query).list();
                se.getTransaction().commit();
                se.close();
                for(int t=0 ;t<Appointments.size();t++){
                    if(!Appointments.get(t).getStatus().toLowerCase().equals("pending")){
                        Appointments.remove(t);
                    }
                }
               return Appointments;
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        public List<MyPatient> SearchInPatients(String phone, int id) {
            Session se = NewHIbernateUtile.getSessionFactory().openSession();
            se.beginTransaction();
            List<MyPatient> Patients =new ArrayList<MyPatient>();

            Query query =se.createQuery("from MyPatient where doc_id = :TU and phone like :UG");
            query.setParameter( "UG", "%"+phone+"%");
            query.setParameter("TU",id);
            String s= ((org.hibernate.query.Query) query).getQueryString().toLowerCase();
            System.out.println(s);
            Patients  = ((org.hibernate.query.Query) query).list();
            se.getTransaction().commit();
            se.close();
            return Patients;
        }
//
//    public  boolean saveLocation(Locations  clinicLocation){
//        try {
//
//            Session se = NewHIbernateUtile.getSessionFactory().openSession();
//            se.beginTransaction();
//            se.save(clinicLocation);
//            se.getTransaction().commit();
//            se.close();
//            return true;
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//    }
//
//    public boolean updateLocation(Locations clinicLocation) {
//        try {
//
//            Session se = NewHIbernateUtile.getSessionFactory().openSession();
//            se.beginTransaction();
//            se.update(clinicLocation);
//            se.getTransaction().commit();
//            se.close();
//            return true;
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//    }
//
//    public Locations getLocationById(int id) {
//        try {
//            System.out.println("now am in get location by id");
//            Session se = NewHIbernateUtile.getSessionFactory().openSession();
//            se.beginTransaction();
//            Locations clinicLocation =new Locations();
//            clinicLocation.setId(id);
//            clinicLocation =se.get(Locations.class,id);
//            se.getTransaction().commit();
//            se.close();
//            System.out.println("/n /n /n \n location"+clinicLocation);
//            return clinicLocation;
//        }
//        catch (Exception e)
//        {
//            return new Locations();
//        }
    }

