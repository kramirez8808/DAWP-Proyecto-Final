package dawp.proyecto.controller;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.ProductoService;
import dawp.proyecto.service.CategoriaService;
import dawp.proyecto.impl.FirebaseStorageServiceImpl;
import dawp.proyecto.service.MarcaService;
import dawp.proyecto.service.EstiloService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    //Objeto ProductoService: Servicio para gestionar los productos
    @Autowired
    ProductoService productoService;

    //Objeto CategoriaService: Servicio para gestionar las categorias
    @Autowired
    CategoriaService categoriaService;

    //Objeto MarcaService: Servicio para gestionar las marcas
    @Autowired
    MarcaService marcaService;

    //Objeto EstiloService: Servicio para gestionar los estilos
    @Autowired
    EstiloService estiloService;
    
    //Listado: Devuelve una vista que muestra una lista con todos los productos
    @GetMapping("/")
    public String listado(Model model) {
        List<Producto> lista = productoService.getProductos();
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());

        model.addAttribute("categorias", categoriaService.getCategoriasActivas(true));
        model.addAttribute("marcas", marcaService.getMarcasActivas(true));
        model.addAttribute("estilos", estiloService.getEstilosActivos(true));

        return "/producto/listado";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            @RequestParam("estado") String estado){     
        
        if (estado.equals("1")) {
            producto.setActivo(false);
        } else {
            producto.setActivo(true);
        }
                
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "producto", 
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/productos/";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/productos/";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);

        List<Producto> lista = productoService.getProductos();
        model.addAttribute("totalProductos", lista.size());

        model.addAttribute("categorias", categoriaService.getCategoriasActivas(true));
        model.addAttribute("marcas", marcaService.getMarcasActivas(true));
        model.addAttribute("estilos", estiloService.getEstilosActivos(true));
        
        return "/producto/modifica";
    }

    @GetMapping("/tienda/")
    public String listadoTienda(Model model) {

        List<Producto> lista = productoService.getProductosActivos(true);
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());

        return "/producto/tienda";
    }

    @GetMapping("/detalle/{idProducto}")
    public String productoDetalle(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        return "/producto/detalle";
    }
}
