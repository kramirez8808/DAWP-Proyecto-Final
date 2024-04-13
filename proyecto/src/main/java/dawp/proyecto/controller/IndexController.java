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
import dawp.proyecto.service.ProductoService;
import dawp.proyecto.service.CategoriaService;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.MarcaService;

@Controller
public class IndexController {

    @Autowired
    ProductoService productoService;

    @Autowired
    MarcaService marcaService;

    @RequestMapping("/")
    public String index(Model model) {
        
        List<Producto> lista = productoService.getProductosActivos(true);
        model.addAttribute("productos", lista);

        return "index";
    }

    
}
