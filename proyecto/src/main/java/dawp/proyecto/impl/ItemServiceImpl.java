package dawp.proyecto.impl;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.dao.FacturaDao;
import dawp.proyecto.dao.ProductoDao;
import dawp.proyecto.dao.VentaDao;
import dawp.proyecto.domain.Factura;
import dawp.proyecto.domain.Item;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.domain.Usuario;
import dawp.proyecto.domain.Venta;
import dawp.proyecto.service.ItemService;
import static dawp.proyecto.service.ItemService.listaItems;
import dawp.proyecto.service.UsuarioService;

@Service
public class ItemServiceImpl implements ItemService {
    
    //Se retorna la lista de productos del carrito
    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Se añade un producto al carrito
    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        
        //Si el producto no existe en el carrito, se añade con cantidad = 1
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    //Se elimina un producto del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                break;
            }
        }

        //Si el producto existe en el carrito, se elimina
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Se recorre el carrito y se obtiene un producto para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }

    //Se actualiza la cantidad de un producto en el carrito
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    //Interface para obtener el usuario autenticado
    @Autowired
    private UsuarioService usuarioService;

    //Interfaces para acceder a la Tabla Factura
    @Autowired
    private FacturaDao facturaDao;

    //Interfaces para acceder a la Tabla Venta
    @Autowired
    private VentaDao ventaDao;

    //Interfaces para acceder a la Tabla Producto
    @Autowired
    private ProductoDao productoDao;

    // Metodo para generar un factura de los productos del carrito
    @Override
    public void facturar() {
        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            return;
        }

        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        if (usuario == null) {
            return;
        }

        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion()
                    + " Cantidad: " + i.getCantidad()
                    + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
            ventaDao.save(venta);
            Producto producto = productoDao.getReferenceById(i.getIdProducto());
            producto.setExistencias(producto.getExistencias()-i.getCantidad());
            productoDao.save(producto);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
}
