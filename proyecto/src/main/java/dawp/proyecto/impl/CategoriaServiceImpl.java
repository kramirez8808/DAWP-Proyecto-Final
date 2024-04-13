package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.CategoriaDao;
import dawp.proyecto.domain.Categoria;
import dawp.proyecto.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    //Objeto CategoriaDAO
    @Autowired
    private CategoriaDao categoriaDao;
    
    //Método GetCategoria
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        List<Categoria> lista = categoriaDao.findAll();

        return lista;
    }

    //Método GetCategorias
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategoriasActivas(boolean activos) {
        List<Categoria> lista = categoriaDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }
    
    //Método GetCategoria
    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    //Método Save
    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    //Método Delete
    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    //Método BuscarPorDescripcion
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarPorDescripcion(String descripcion) {
        return categoriaDao.findByDescripcion(descripcion);
    }

}
