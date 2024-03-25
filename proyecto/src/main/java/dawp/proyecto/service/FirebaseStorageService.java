package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// ------ INTERNAL IMPORTS ------

@Service
public interface FirebaseStorageService {
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BucketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "#.appspot.com";

    //Esta es la ruta básica de este proyecto
    final String rutaSuperiorStorage = "#";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "#.json";

}
