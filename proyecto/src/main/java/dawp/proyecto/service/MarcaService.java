package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Marca;

public interface MarcaService {
    
    //GetMarcas: Devuelve una lista con todos los marcas
    public List<Marca> getMarcas();

    //GetMarcasActivas: Devuelve una lista con todos los marcas activos
    public List<Marca> getMarcasActivas(boolean activos);

    //GetMarca: Devuelve un marca por su ID
    public Marca getMarca(Marca marca);

    //Save: Guarda o actualiza un marca existente
    public void save(Marca marca);

    //Delete: Elimina un marca existente
    public void delete(Marca marca);

    //BuscarPorDescripcion: Busca una marca por su descripcion
    public List<Marca> buscarPorDescripcion(String descripcion);

}
