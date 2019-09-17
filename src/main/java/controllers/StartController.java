package controllers;

import HibernateSecurity.HibernateCRUDPatient;
import SecurityLayer.UserSession;
import Services.DoctorUsers;
import Services.EmailService;
import Services.TokenReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//well tested
@RestController
public class StartController {
    @Autowired
    private EmailService mailS ;


    @ResponseBody
    @RequestMapping(value = "/IsAuthenticated", method = RequestMethod.POST)
    public Boolean IsAuthenticated(@RequestHeader String token ){
    HibernateCRUDPatient hibr = new HibernateCRUDPatient();
    return hibr.checktoken(token);
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenReturn loginWebService(@RequestParam("password") String pass, @RequestParam("username") String username) {
        HibernateCRUDPatient hibera = new HibernateCRUDPatient();

        System.out.println(username +"****"+pass);
        System.out.println(hibera.login(username, pass));
        if (hibera.login(username, pass) != 0) {
            String token = generateToken();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            UserSession userSession = new UserSession();
            userSession.setToken(token);
            userSession.setUserID(hibera.login(username, pass));
            userSession.setLast(now);
            HibernateCRUDPatient hibra = new HibernateCRUDPatient();
            boolean save= hibra.saveSession(userSession);
            if (save) {
//                List<String> map = new ArrayList<String>();
//                map.add(token);
//                map.add("1");
//                return map;
                TokenReturn tokenReturn = new TokenReturn(token , 1);
                return tokenReturn;

            }
        }
//            List<String> map = new ArrayList<String>();
//            map.add("0");
//            retu  rn map;
        TokenReturn tokenReturn = new TokenReturn("" , 0);
        return tokenReturn;
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public boolean logoutWebService(HttpServletRequest req) {
        String token = req.getParameter("token");
        HibernateCRUDPatient hibra = new HibernateCRUDPatient();
        boolean save= hibra.deleteSession(token);
        if (save) {
            return true;
        }
            return false;

    }
    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }

    @RequestMapping(value = "/ForgetPassword", method = RequestMethod.GET)
    public boolean ForgetPassword( @RequestParam(value = "Mail") String Mail) {
        try {
            //HibernateCRUDPatient hib = new HibernateCRUDPatient();
           // DoctorUsers user = hib.GetUserbyMail(Mail);
           // String message = "your password is "+ user.getPassword();

            mailS.sendMail("mohaosa666@gmail.com" , "confirmation", "asdxfasdxf");
            return true;
        }catch (Exception e){
             e.printStackTrace();
            return false;
        }
    }

 }
