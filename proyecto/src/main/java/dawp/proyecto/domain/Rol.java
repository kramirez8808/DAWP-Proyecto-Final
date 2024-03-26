package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

// ------ INTERNAL IMPORTS ------

@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol; //Hibernate lo transforma/MySQL => id_rol PK

    @NotEmpty
    private String nombre; //MySQL => nombre

    @Column(name = "id_usuario")
    private Long idUsuario; //MySQL => id_usuario FK
}
