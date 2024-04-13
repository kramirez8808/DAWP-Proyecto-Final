/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import dawp.proyecto.domain.Marca;
import dawp.proyecto.service.MarcaService;
import dawp.proyecto.impl.FirebaseStorageServiceImpl;

@Controller
@RequestMapping("/marcas")
public class MarcaController {
    
    //Objeto MarcaService: Servicio para gestionar las marcas
    @Autowired
    MarcaService marcaService;
    
    //Listado: Devuelve una vista quue muestra una lista con todas las marcas
    @GetMapping("/")
    public String listado(Model model) {
        List<Marca> lista = marcaService.getMarcas();
        model.addAttribute("marcas", lista);
        model.addAttribute("totalMarcas", lista.size());
        return "/marca/listado";
    }

    //Objeto FirebaseStorageService: Servicio para almacenar imagenes en Firebase
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    //Guardar: Guarda una nueva marca en la base de datos y redirige a la vista de listado
    @PostMapping("/guardar")
    public String marcaGuardar(Marca marca,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            @RequestParam("estado") String estado) {
                
        if (estado.equals("1")) {
            marca.setActivo(false);
        } else {
            marca.setActivo(true);
        }

        if (!imagenFile.isEmpty()) {
            marcaService.save(marca);
            marca.setImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "marca", 
                            marca.getIdMarca()));
        }
        marcaService.save(marca);
        return "redirect:/marcas/";
    }

    //Eliminar: Elimina una marca de la base de datos y redirige a la vista de listado
    @GetMapping("/eliminar/{idMarca}")
    public String marcaEliminar(Marca marca) {
        marcaService.delete(marca);
        return "redirect:/marcas/";
    }

    //Modificar: Devuelve la vista para modificar una marca
    @GetMapping("/modificar/{idMarca}")
    public String marcaModificar(Marca marca, Model model) {
        List<Marca> lista = marcaService.getMarcas();
        model.addAttribute("totalMarcas", lista.size());

        marca = marcaService.getMarca(marca);
        model.addAttribute("marca", marca);
        return "/marca/modifica";
    }


}
