package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Marca;

public interface MarcaDao extends JpaRepository<Marca, Long> {
    
    // Método para buscar Categoria por Descripcion
    List<Marca> findByDescripcion(String descripcion);
}
