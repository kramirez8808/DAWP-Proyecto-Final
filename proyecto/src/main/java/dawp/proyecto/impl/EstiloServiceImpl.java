package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.EstiloDao;
import dawp.proyecto.domain.Estilo;
import dawp.proyecto.service.EstiloService;

@Service
public class EstiloServiceImpl implements EstiloService {
    
    //Objeto EstiloDAO
    @Autowired
    private EstiloDao estiloDao;

    //Método GetEstilos
    @Override
    @Transactional(readOnly = true)
    public List<Estilo> getEstilos() {
        List<Estilo> lista = estiloDao.findAll();

        return lista;
    }

    //Método GetEstilosActivos
    @Override
    @Transactional(readOnly = true)
    public List<Estilo> getEstilosActivos(boolean activos) {
        List<Estilo> lista = estiloDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

    //Método GetEstilo
    @Override
    @Transactional(readOnly = true)
    public Estilo getEstilo(Estilo estilo) {
        return estiloDao.findById(estilo.getIdEstilo())
        .orElse(null);
    }

    //Método Save
    @Override
    @Transactional
    public void save(Estilo estilo) {
        estiloDao.save(estilo);
    }

    //Método Delete
    @Override
    @Transactional
    public void delete(Estilo estilo) {
        estiloDao.delete(estilo);
    }
    
    //Método BuscarPorDescripcion
    @Override
    @Transactional(readOnly = true)
    public List<Estilo> buscarPorDescripcion(String descripcion) {
        return estiloDao.findByDescripcion(descripcion);
    }
}
