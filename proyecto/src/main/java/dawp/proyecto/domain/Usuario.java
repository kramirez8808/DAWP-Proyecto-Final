package dawp.proyecto.domain;

// ------ IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_usuario")
    private Long idUsuario; //MySQL => id_usuario PK
    private String username; //MySQL => username
    private String password; //MySQL => password
    private String nombre; //MySQL => nombre
    private String apellido; //MySQL => apellido
    private String email; //MySQL => email
    private String telefono; //MySQL => telefono
    private String imagen; //MySQL => imagen
    private Boolean activo; //MySQL => activo

    //Constructores
    public Usuario() {
    }

    public Usuario(Long idUsuario, String username, String password, String nombre, String apellido, String email, String telefono, String imagen, Boolean activo) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.imagen = imagen;
        this.activo = activo;
    }
}
