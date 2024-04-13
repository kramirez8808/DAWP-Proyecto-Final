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
import dawp.proyecto.domain.Categoria;
import dawp.proyecto.service.CategoriaService;
import dawp.proyecto.impl.FirebaseStorageServiceImpl;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    //Objeto CategoriaService: Servicio para gestionar las categorias
    @Autowired
    CategoriaService categoriaService;
    
    //Listado: Devuelve una vista quue muestra una lista con todas las categorias
    @GetMapping("/")
    public String listado(Model model) {
        List<Categoria> lista = categoriaService.getCategorias();
        model.addAttribute("categorias", lista);
        model.addAttribute("totalCategorias", lista.size());
        return "/categoria/listado";
    }

    //Objeto FirebaseStorageService: Servicio para almacenar imagenes en Firebase
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    //Guardar: Guarda una nueva categoria en la base de datos y redirige a la vista de listado
    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            @RequestParam("estado") String estado) {
                
        if (estado.equals("1")) {
            categoria.setActivo(false);
        } else {
            categoria.setActivo(true);
        }

        if (!imagenFile.isEmpty()) {
            categoriaService.save(categoria);
            categoria.setImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "categoria", 
                            categoria.getIdCategoria()));
        }
        categoriaService.save(categoria);
        return "redirect:/categorias/";
    }

    //Eliminar: Elimina una categoria de la base de datos y redirige a la vista de listado
    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categorias/";
    }

    //Modificar: Devuelve la vista para modificar una categoria
    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        List<Categoria> lista = categoriaService.getCategorias();
        model.addAttribute("totalCategorias", lista.size());

        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }

}
