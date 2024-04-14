package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

// ------ INTERNAL IMPORTS ------

@Data
@Entity
@Table(name = "venta")
public class Venta implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_venta")
    private Long idVenta; //MySQL => id_venta PK
    private Long idFactura; //MySQL => id_factura FK
    private Long idProducto; //MySQL => id_producto FK
    private Double precio; //MySQL => precio
    private Integer cantidad; //MySQL => cantidad

    //Constructores
    public Venta() {
    }

    public Venta(Long idFactura, Long idProducto, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
}
