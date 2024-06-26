package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

// ------ INTERNAL IMPORTS ------

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
    
    //Relación con la tabla Producto
    @OneToMany
    @JoinColumn(name = "id_marca", insertable = false, updatable = false) //No se puede insertar ni actualizar, es solo para consultas
    List<Producto> productos; 

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
