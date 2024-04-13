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

    // Lista de productos con precio entre ordendados por descripción ConsultaAmpliada
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    //Lista de productos utilizando consultas con JPQL    
    public List<Producto> metodoJPQL(double precioInf, double precioSup);

    //Lista de productos utilizando consultas con SQL Nativo
    public List<Producto> metodoNativo(double precioInf, double precioSup);
}
