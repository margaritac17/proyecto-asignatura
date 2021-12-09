package co.edu.uniquindio.proyecto.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String body, String subject) throws Exception{

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("margaritacente2001@gmail.com");
        mimeMessageHelper.setTo("margaritacente2001@gmail.com");
        mimeMessageHelper.setText(body,true);
        mimeMessageHelper.setSubject(subject);


        mailSender.send(mimeMessage);
        System.out.println("Email enviado");
    }


}
