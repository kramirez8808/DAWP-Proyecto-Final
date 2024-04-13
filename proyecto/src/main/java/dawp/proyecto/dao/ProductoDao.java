package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    
    //Metodo para consultar productos por su Marca
    @Query(nativeQuery=true,
            value="SELECT * FROM producto WHERE producto.id_marca = :idMarca")
    public List<Producto> queryByMarca(@Param("idMarca") Long idMarca); 

    //Metodo para consultar productos por su Categoria
    @Query(nativeQuery=true,
            value="SELECT * FROM producto WHERE producto.id_categoria = :idCategoria")
    public List<Producto> queryByCategoria(@Param("idCategoria") Long idCategoria);

    //Metodo para consultar productos por su Estilo
     @Query(nativeQuery=true,
            value="SELECT * FROM producto WHERE producto.id_estilo = :idEstilo")
    public List<Producto> queryByEstilo(@Param("idEstilo") Long idEstilo);

}
