package dawp.proyecto.domain;

// ------ IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_rol")
    private Long idRol; //MySQL => id_rol PK
    private String descripcion; //MySQL => descripcion

    //Constructores
    public Rol() {
    }

    public Rol(Long idRol, String nombre, String descripcion) {
        this.idRol = idRol;
        this.descripcion = descripcion;
    }
    
}
