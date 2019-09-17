package controllers;


import HibernateSecurity.NewHIbernateUtile;
import Services.MyPatient;
import Services.PatientsImages;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
public class ImagesController {

    public static String UPLOADED_FOLDER = "E:/patients_uploads/";
          //  "E://spring boot , security , hibernate notes//";
    //"/home/m_ewies/"

//    @RequestMapping(value = "/Images", method = RequestMethod.GET)
//    public ArrayList GetImages (@RequestParam int id , @RequestParam String token){
//        ArrayList my_links  =null;
//        Session se = NewHIbernateUtile.getSessionFactory().openSession();
//        se.beginTransaction();
//        Query qe = se.createQuery("select uploadDate,link from PatientsImages where paatient_id=:Id");
//        qe.setParameter("Id",id);
//        my_links = (ArrayList) qe.list();
//        se.getTransaction().commit();
//        return my_links;
//
//    }

    @RequestMapping(value = "/UploadImage" , method = RequestMethod.POST) // //new annotation since 4.3
    public String singleFileUpload(@RequestBody MultipartFile file,@RequestParam String token , @RequestParam int Patient_id ) {
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
                Session se = NewHIbernateUtile.getSessionFactory().openSession();
                se.beginTransaction();
                //se.save(myImage);
                 MyPatient pat = (MyPatient) se.get(MyPatient.class,Patient_id);
               if(pat !=null) {
                   pat.getImages().add(myImage);
                   se.update(pat);
                   se.getTransaction().commit();
               }
                return myImage.getLink() ;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return "Uploaded file is empty";
    }




    @RequestMapping(value = "/Image" , method = RequestMethod.GET)
    public byte[] getImage(@RequestParam String link, @RequestParam String token) throws IOException {

        InputStream is = new FileInputStream(link);
        BufferedImage img = ImageIO.read(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(img,"PNG", bos);
        return bos.toByteArray();
    }

    private String generateid() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[19];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }



}
