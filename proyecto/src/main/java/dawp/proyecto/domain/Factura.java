package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

// ------ INTERNAL IMPORTS ------

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
    private Date fecha; //MySQL => fecha
    private Double total; //MySQL => total
    private int estado; //MySQL => estado

    //Constructores
    public Factura() {
    }

    public Factura(Long idUSuario) {
        this.idUsuario = idUSuario;
        this.fecha = Calendar.getInstance().getTime();
        this.estado=1;
    }
}
