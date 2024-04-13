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

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Estilo;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.EstiloService;
import dawp.proyecto.service.ProductoService;

@Controller
@RequestMapping("/estilos")
public class EstiloController {
    
    //Objeto EstiloService: Servicio para gestionar las estilos
    @Autowired
    EstiloService estiloService;
    
    //Listado: Devuelve una vista quue muestra una lista con todas las estilos
    @GetMapping("/")
    public String listado(Model model) {
        List<Estilo> lista = estiloService.getEstilos();
        model.addAttribute("estilos", lista);
        model.addAttribute("totalEstilos", lista.size());
        return "/estilo/listado";
    }
    
    //Guardar: Guarda una nueva estilo en la base de datos y redirige a la vista de listado
    @PostMapping("/guardar")
    public String estiloGuardar(Estilo estilo,
            @RequestParam("estado") String estado) {
                
        if (estado.equals("1")) {
            estilo.setActivo(false);
        } else {
            estilo.setActivo(true);
        }

        estiloService.save(estilo);
        return "redirect:/estilos/";
    }

    //Eliminar: Elimina una estilo de la base de datos y redirige a la vista de listado
    @GetMapping("/eliminar/{idEstilo}")
    public String estiloEliminar(Estilo estilo) {
        estiloService.delete(estilo);
        return "redirect:/estilos/";
    }

    //Modificar: Devuelve la vista para modificar una estilo
    @GetMapping("/modificar/{idEstilo}")
    public String estiloModificar(Estilo estilo, Model model) {
        List<Estilo> lista = estiloService.getEstilos();
        model.addAttribute("totalEstilos", lista.size());

        estilo = estiloService.getEstilo(estilo);
        model.addAttribute("estilo", estilo);
        return "/estilo/modifica";
    }

    //Objeto ProductoServce: Servicio para gestionar los productos
    @Autowired
    ProductoService productoService;

    //Tienda: Muestra una vista de Productos para buscar por Estilo
    @GetMapping("/tienda/")
    public String tienda(Model model) {

        List<Producto> productos = productoService.getProductosActivos(true);
        List<Estilo> estilos = estiloService.getEstilosActivos(true);

        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("estilos", estilos);

        return "/estilo/tienda";

    }

    //Busqueda: Muestra el resultado de la busqueda de productos por Estilo
    @PostMapping("/busqueda/")
    public String queryByEstilo(@RequestParam("idEstilo") String idEstilo , Model model) {
        Long idEstiloLong = Long.parseLong(idEstilo);

        List<Producto> productos = productoService.queryByEstilo(idEstiloLong);
        List<Estilo> estilos = estiloService.getEstilosActivos(true);

        model.addAttribute("idEstilo", idEstilo);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("estilos", estilos);

        return "/estilo/tienda";
    }

}
