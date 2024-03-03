package dawp.proyecto.domain;

// ------ IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_factura")
    private Long idFactura; //MySQL => id_factura PK
    private Long idUsuario; //MySQL => id_usuario FK
    private String fecha; //MySQL => fecha
    private Double total; //MySQL => total
    private String estado; //MySQL => estado

    //Constructores
    public Factura() {
    }

    public Factura(Long idFactura, Long idUsuario, String fecha, Double total, String estado) {
        this.idFactura = idFactura;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }
}
