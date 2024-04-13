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

    //Save: Guarda o actualiza un producto existente
    public void save(Producto producto);

    //Delete: Elimina un producto existente
    public void delete(Producto producto);

    //FindByMarcaId: Devuelve una lista con todos los productos de una marca
    public List<Producto> queryByMarca(Long marcaId);

    //FindByCategoriaId: Devuelve una lista con todos los productos de una categoria
    public List<Producto> queryByCategoria(Long categoriaId);

    //FindByEstiloId: Devuelve una lista con todos los productos de un estilo
    public List<Producto> queryByEstilo(Long estiloId);
    
}
