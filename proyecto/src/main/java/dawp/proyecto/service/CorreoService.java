package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import jakarta.mail.MessagingException;

// ------ INTERNAL IMPORTS ------

public interface CorreoService {
    
    //Metodo para enviar un correo HTML
    public void enviarCorreoHtml(String para, String asunto, String contenidoHtml) throws MessagingException;

}
