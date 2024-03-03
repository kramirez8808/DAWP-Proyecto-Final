package dawp.proyecto.domain;

// ------ IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "marca")
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_marca")
    private Long idMarca; //MySQL => id_marca PK
    private String descripcion; //MySQL => descripcion
    private String imagen; //MySQL => imagen
    private boolean activo; //MySQL => activo

    //Constructores
    public Marca() {
    }

    public Marca(Long idMarca, String descripcion, String imagen, Boolean activo) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
    }
}
