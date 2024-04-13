package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

// ------ INTERNAL IMPORTS ------

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Campo Auto-Incremental
    @Column(name = "id_producto")
    private Long idProducto; //Hibernate lo transforma/MySQL => id_producto PK
    private String descripcion; //MySQL => descripcion
    private String detalle; //MySQL => detalle
    private double precio; //MySQL => precio
    private int existencias; //MySQL => existencias
    private String imagen; //MySQL => ruta_imagen
    private boolean activo; //MySQL => activo
    
    // Relación con la tabla Categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    Categoria categoria; //MySQL => id_categoria FK

    // Relación con la tabla Marca
    @ManyToOne
    @JoinColumn(name = "id_marca")
    Marca marca; //MySQL => id_marca FK
    
    // Relación con la tabla Estilo
    @ManyToOne
    @JoinColumn(name = "id_estilo")
    Estilo estilo; //MySQL => id_categoria FK

    //Constructores
    public Producto() {
    }

    public Producto(String descripcion, String detalle, double precio, int existencias, String imagen, boolean activo) {
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
        this.activo = activo;
    }
    
}
