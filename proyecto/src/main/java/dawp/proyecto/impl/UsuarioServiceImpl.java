package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.RolDao;
import dawp.proyecto.dao.UsuarioDao;
import dawp.proyecto.domain.Rol;
import dawp.proyecto.domain.Usuario;
import dawp.proyecto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;

    //Método para obtener todos los usuarios
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    //Método para obtener un usuario por su id
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    //Método para obtener un usuario por su username
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    //Método para obtener un usuario por su username y password
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    //Método para obtener un usuario por su username o correo
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }

    //Método para saber si existe un usuario por su username o correo
    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }

    //Método para guardar un usuario
    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        usuario=usuarioDao.save(usuario);
        if (crearRolUser) {  //Si se está creando el usuario, se crea el rol por defecto "USER"
            Rol rol = new Rol();
            rol.setNombre("ROLE_USER");
            rol.setIdUsuario(usuario.getIdUsuario());
            rolDao.save(rol);
        }
    }

    //Método para eliminar un usuario
    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}

