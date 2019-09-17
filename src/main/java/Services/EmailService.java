package Services;

import com.google.api.services.gmail.model.ListSendAsResponse;
import com.google.api.services.gmail.model.SendAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
/**
 * Created by kiranreddy on 22/04/17.
 */
@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    private SimpleMailMessage simpleMailMessage;
    public void sendMail(String toEmail, String subject, String message) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("mohasaber@gmail.com");
        javaMailSender.send(mailMessage);
        ////////////////////
        ///////////////////


        /*

          MimeMessage messagee = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messagee, true);

	   try{

        helper.setFrom(mailMessage.getFrom());
        helper.setTo(mailMessage.getTo());
        helper.setSubject(mailMessage.getSubject());

            helper.setText("***************");
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        FileSystemResource file = new FileSystemResource("E:\\java-code39.png");
        try {
            helper.addAttachment(file.getFilename(), file);
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
         */

    }



}

