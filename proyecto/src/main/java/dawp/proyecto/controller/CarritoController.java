package dawp.proyecto.controller;

// ------ EXTERNAL IMPORTS ------
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Item;
import dawp.proyecto.domain.Producto;
import dawp.proyecto.service.ItemService;
import dawp.proyecto.service.ProductoService;

@Controller
public class CarritoController {
    
    // Interface ItemService: Servicio para gestionar los items
    @Autowired
    private ItemService itemService;

    // Interface ProductoService: Servicio para gestionar los productos
    @Autowired
    private ProductoService productoService;

    // Ver el carrito
    @GetMapping("/carrito/")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal", 
                carritoTotalVenta);
        return "/carrito/listado";
    }    
   
    // Agregar carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2 == null) {
            Producto producto = productoService.getProducto(item);
            item2 = new Item(producto);
        }
        itemService.save(item2);
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

    // Modificar producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    // Eliminar producto del carrito
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/";
    }

    // Actualizar la cantidad de un producto en el carrito
    // @PostMapping("/carrito/guardar")
    // public String guardarItem(Item item) {
    //     itemService.actualiza(item);
    //     return "redirect:/carrito/";
    // }

    // @GetMapping("/carrito/guardar/{idProducto}")
    // public String guardarItem(@PathVariable(value="idProducto") Long idProducto,
    //     @RequestParam(value="cantidad") Integer cantidad) {
    //     Item item = new Item();
    //     item.setIdProducto(idProducto);
    //     item = itemService.get(item);
    //     item.setCantidad(cantidad);
    //     itemService.actualiza(item);
    //     item = new Item();
    //     return "redirect:/carrito/";
    // }

    // @PostMapping("/carrito/guardar/{idProducto}")
    // public String guardarItem(@RequestParam(value="idProducto") Long idProducto,
    //     @RequestParam(value="cantidad") Integer cantidad) {
    //     Item item = new Item();
    //     item.setIdProducto(idProducto);
    //     item = itemService.get(item);
    //     item.setCantidad(cantidad);
    //     itemService.actualiza(item);
    //     item = new Item();
    //     return "redirect:/carrito/";
    // }

    @PostMapping("/carrito/guardar/{idProducto}")
    public String guardarItem(@RequestParam(value="idProducto") Long idProducto,
        @RequestParam(value="cantidad") Integer cantidad) {
        Item item = new Item();
        item.setIdProducto(idProducto);
        item = itemService.get(item);
        item.setCantidad(cantidad);
        itemService.actualiza(item);
        return "redirect:/carrito/";
    }

    // Facturar el carrito
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }

}
