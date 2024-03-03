package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Marca;

public interface MarcaService {
    
    //GetMarcas: Devuelve una lista con todas las marcas
    public List<Marca> getMarcas();

    //GetMarcasActivas: Devuelve una lista con todas las marcas activas
    public List<Marca> getMarcasActivas(boolean activos);

    //GetMarca: Devuelve una marca por su ID
    public Marca getMarca(Marca marca);

    //SaveMarca: Guarda o actualiza una marca existente
    public void saveMarca(Marca marca);

    //DeleteMarca: Elimina una marca existente
    public void deleteMarca(Marca marca);   
}
