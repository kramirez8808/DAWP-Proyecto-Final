package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.UsuarioDao;
import dawp.proyecto.domain.Usuario;
import dawp.proyecto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    //Objeto UsuarioDAO
    @Autowired
    private UsuarioDao usuarioDao;

    //Método GetUsuario
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        List<Usuario> lista = usuarioDao.findAll();

        return lista;
    }

    //Método GetUsuariosActivos
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuariosActivos(boolean activos) {
        List<Usuario> lista = usuarioDao.findAll();

        if (activos) {
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;
    }

    //Método GetUsuario
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario())
        .orElse(null);
    }

    //Método SaveUsuario
    @Override
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    //Método DeleteUsuario
    @Override
    @Transactional
    public void deleteUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
    
}
