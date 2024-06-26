package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.ProductoDao;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    //Objeto ProductoDAO
    @Autowired
    private ProductoDao productoDao;

    //Método GetProducto
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        List<Producto> lista = productoDao.findAll();

        return lista;
    }

    //Método GetProductosActivos
    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductosActivos(boolean activos) {
        List<Producto> lista = productoDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

    //Método GetProducto
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto())
        .orElse(null);
    }

    //Método Save
    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    //Método Delete
    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
    //Método FindByMarcaId
    @Override
    @Transactional(readOnly=true)
    public List<Producto> queryByMarca(Long marcaId) {
        return productoDao.queryByMarca(marcaId);
    }

    //Método FindByCategoriaId
    @Override
    @Transactional(readOnly=true)
    public List<Producto> queryByCategoria(Long categoriaId) {
        return productoDao.queryByCategoria(categoriaId);
    }

    //Método FindByEstiloId
    @Override
    @Transactional(readOnly=true)
    public List<Producto> queryByEstilo(Long estiloId) {
        return productoDao.queryByEstilo(estiloId);
    }

}
