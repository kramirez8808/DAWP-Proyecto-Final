package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Estilo;

public interface EstiloDao extends JpaRepository<Estilo, Long> {
    
    // Método para buscar Categoria por Descripcion
    List<Estilo> findByDescripcion(String descripcion);
}
