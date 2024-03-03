package dawp.proyecto.domain;

// ------ IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_producto")
    private Long idProducto; //MySQL => id_producto PK
    private Long idMarca; //MySQL => id_marca FK
    private String descripcion; //MySQL => descripcion
    private String detalle; //MySQL => detalle
    private Double precio; //MySQL => precio
    private Integer existencias; //MySQL => existencias
    private String imagen; //MySQL => imagen
    private Boolean activo; //MySQL => activo

    //Constructores
    public Producto() {
    }

    public Producto(Long idProducto, Long idMarca, String descripcion, String detalle, Double precio, Integer existencias, String imagen, Boolean activo) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
        this.activo = activo;
    }
}
