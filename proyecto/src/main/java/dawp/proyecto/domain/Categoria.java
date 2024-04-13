package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

// ------ INTERNAL IMPORTS ------


@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Campo Auto-Incremental
    @Column(name = "id_categoria")
    private Long idCategoria; //Hibernate lo transforma/MySQL => id_categoria PK
    private String descripcion; //MySQL => descripcion
    private String imagen; //MySQL => ruta_imagen
    private boolean activo; //MySQL => activo

    //Relación con la tabla Producto
    @OneToMany
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false) //No se puede insertar ni actualizar, es solo para consultas
    List<Producto> productos; 

    //Constructores
    public Categoria() {
    }

    public Categoria(String descripcion, String imagen, boolean activo) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
    }
}
