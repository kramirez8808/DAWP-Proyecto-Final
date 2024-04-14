package dawp.proyecto.controller;

// ------ EXTERNAL IMPORTS ------
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.service.ProductoService;
import dawp.proyecto.domain.Item;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.ItemService;
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

    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/refrescarBoton")
    public ModelAndView refrescarBoton(Model model) { 
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
}
