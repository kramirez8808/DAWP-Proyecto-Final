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
    //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery=true,
            value="SELECT * FROM producto WHERE producto.id_marca = :idMarca")
    public List<Producto> queryMarca(@Param("idMarca") Long idMarca); 

    //Metodo para consultar productos por su Categoria
    public List<Producto> findByCategoriaId(Long categoriaId);

    //Metodo para consultar productos por su Estilo
    public List<Producto> findByEstiloId(Long estiloId);
}
