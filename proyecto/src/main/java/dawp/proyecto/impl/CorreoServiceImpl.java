package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.service.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService {
    
    // Objeto JavaMailSender para el envío de correos
    @Autowired
    private JavaMailSender mailSender;

    // Método para enviar el correo en formato HTML
    @Override
    public void enviarCorreoHtml(String para, String asunto, String contenidoHtml) throws MessagingException {
          
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper 
                = new MimeMessageHelper(message, 
                        true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml,true);
        mailSender.send(message);
    }
}
