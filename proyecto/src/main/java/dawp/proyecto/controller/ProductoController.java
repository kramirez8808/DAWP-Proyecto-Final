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

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        List<Producto> lista = productoService.getProductosActivos(false);
        model.addAttribute("productos", lista);
        model.addAttribute("totalProductos", lista.size());
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "/producto/listado";
    }
    
    @GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "producto", 
                            producto.getIdProducto()));
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "/producto/modifica";
    }
}
