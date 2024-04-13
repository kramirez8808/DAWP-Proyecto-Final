// package dawp.proyecto.impl;

// // ------ EXTERNAL IMPORTS ------
// import jakarta.servlet.http.HttpSession;
// import java.util.ArrayList;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;


// // ------ INTERNAL IMPORTS ------
// import dawp.proyecto.dao.UsuarioDao;
// import dawp.proyecto.domain.Rol;
// import dawp.proyecto.domain.Usuario;
// import dawp.proyecto.service.UsuarioDetailsService;

// @Service
// public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    
//     @Autowired
//     private UsuarioDao usuarioDao;

//     @Autowired
//     private HttpSession session;

//     //Método para cargar el usuario por el username
//     @Override
//     @Transactional(readOnly = true)
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         //Busca el usuario por el username en la tabla
//         Usuario usuario = usuarioDao.findByUsername(username);
//         //Si no existe el usuario lanza una excepción
//         if (usuario == null) {
//             throw new UsernameNotFoundException(username);
//         }
//         session.removeAttribute("usuarioImagen");
//         session.setAttribute("usuarioImagen", usuario.getRutaImagen());
//         //Si está acá es porque existe el usuario... sacamos los roles que tiene
//         var roles = new ArrayList<GrantedAuthority>();
//         for (Rol rol : usuario.getRoles()) {   //Se sacan los roles
//             roles.add(new SimpleGrantedAuthority(rol.getNombre()));
//         }
//         //Se devuelve User (clase de userDetails)
//         return new User(usuario.getUsername(), usuario.getPassword(), roles);
//     }
    
// }
