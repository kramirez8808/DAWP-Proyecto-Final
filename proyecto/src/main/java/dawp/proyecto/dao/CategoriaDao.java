package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Categoria;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {
    
    // Método que busca una categoría por su descripción
    List<Categoria> findByDescripcion(String descripcion);
}
