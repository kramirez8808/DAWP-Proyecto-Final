package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Producto;

public interface ProductoService {
    
    //GetProductos: Devuelve una lista con todos los productos
    public List<Producto> getProductos();

    //GetProductosActivos: Devuelve una lista con todos los productos activos
    public List<Producto> getProductosActivos(boolean activos);

    //GetProducto: Devuelve un producto por su ID
    public Producto getProducto(Producto producto);

    //SaveProducto: Guarda o actualiza un producto existente
    public void saveProducto(Producto producto);

    //DeleteProducto: Elimina un producto existente
    public void deleteProducto(Producto producto);
}
