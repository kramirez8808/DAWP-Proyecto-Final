package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

// ------ INTERNAL IMPORTS ------

@Data
@Entity
@Table(name = "estilo")
public class Estilo implements Serializable {
    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name = "id_estilo")
    private Long idEstilo; //MySQL => id_estilo PK
    private String descripcion; //MySQL => descripcion
    private boolean activo; //MySQL => activo
    
    //Relación con la tabla Producto
    @OneToMany
    @JoinColumn(name = "id_estilo", insertable = false, updatable = false) //No se puede insertar ni actualizar, es solo para consultas
    List<Producto> productos; 

    //Constructores
    public Estilo() {
    }

    public Estilo(Long idEstilo, String descripcion, Boolean activo) {
        this.idEstilo = idEstilo;
        this.descripcion = descripcion;
        this.activo = activo;
    }
}

