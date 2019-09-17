package controllers;

import HibernateSecurity.HibernateCRUDPatient;
import HibernateSecurity.NewHIbernateUtile;
import Services.Doctor;
import Services.MyPatient;
import Services.PatientsImages;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import static controllers.ImagesController.UPLOADED_FOLDER;

// well tested
@RestController
public class DoctorController {

    @RequestMapping(value = "/DoctorProfile", method = RequestMethod.PUT)
    public boolean UpdateMydata(@RequestBody Doctor doc , @RequestParam String token ) {
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        return   hiberna.UpdateDoctorData(doc,token);
    }
    @RequestMapping(value = "/DoctorProfile", method = RequestMethod.GET)
    public Doctor GetDoctor( @RequestParam String token  ) {
        System.out.println("getall data invoced"+token  );
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        return hiberna.getDoctorData(token);
    }

    @RequestMapping(value = "/DoctorProfilePic", method = RequestMethod.GET)
    public PatientsImages GetDoctorProfilepic(@RequestParam String token  ) {
        System.out.println("getall data invoced"+token  );
        HibernateCRUDPatient hiberna = new HibernateCRUDPatient();
        return hiberna.getDoctorData(token).getProfilepicture();
    }
    @RequestMapping(value = "/DoctorProfilePic" , method = RequestMethod.PUT) // //new annotation since 4.3
    public String singleFileUpload(@RequestBody MultipartFile file, @RequestParam String token  ) {
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                String name = generateid();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename()
                        .replace(file.getOriginalFilename(),name+".PNG"));
                Files.write(path , bytes);
                PatientsImages myImage = new PatientsImages();
                myImage.setLink(path.toString());
                //LocalDateTime now = LocalDateTime.now();
                String now = LocalDateTime.now().toString();
                myImage.setUploadDate(now);
                System.out.println("link is " +path );

                HibernateCRUDPatient hibra = new HibernateCRUDPatient();
                Doctor doct = hibra.getDoctorData(token);
                doct.setProfilepicture(myImage);
                Session se = NewHIbernateUtile.getSessionFactory().openSession();
                se.beginTransaction();
                se.update(doct);
                se.getTransaction().commit();
                return myImage.getLink() ;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return "Uploaded file is empty";
    }

    private String generateid() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[19];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }
}