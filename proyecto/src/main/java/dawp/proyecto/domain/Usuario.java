package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

// ------ INTERNAL IMPORTS ------

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario; //Hibernate lo transforma/MySQL => id_usuario PK
    
    @NotEmpty
    private String username; //MySQL => username
    
    @NotEmpty
    private String password; //MySQL => password
    private String nombre; //MySQL => nombre
    private String apellidos; //MySQL => apellidos
    private String correo; //MySQL => correo
    private String telefono; //MySQL => telefono
    private String rutaImagen; //MySQL => ruta_imagen
    private boolean activo; //MySQL => activo

    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles; //Relación con la tabla Rol
}
