package dawp.proyecto.dao;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    
}
