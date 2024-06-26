package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.MarcaDao;
import dawp.proyecto.domain.Marca;
import dawp.proyecto.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {
    
    //Objeto MarcaDAO
    @Autowired
    private MarcaDao marcaDao;

    //Método GetMarcas
    @Override
    @Transactional(readOnly = true)
    public List<Marca> getMarcas() {
        List<Marca> lista = marcaDao.findAll();

        return lista;
    }

    //Método GetMarcasActivas
    @Override
    @Transactional(readOnly = true)
    public List<Marca> getMarcasActivas(boolean activos) {
        List<Marca> lista = marcaDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

    //Método GetMarca
    @Override
    @Transactional(readOnly = true)
    public Marca getMarca(Marca marca) {
        return marcaDao.findById(marca.getIdMarca())
        .orElse(null);
    }

    //Método Save
    @Override
    @Transactional
    public void save(Marca marca) {
        marcaDao.save(marca);
    }

    //Método Delete
    @Override
    @Transactional
    public void delete(Marca marca) {
        marcaDao.delete(marca);
    }
    
    //Método BuscarPorDescripcion
    @Override
    @Transactional(readOnly = true)
    public List<Marca> buscarPorDescripcion(String descripcion) {
        return marcaDao.findByDescripcion(descripcion);
    }
}
