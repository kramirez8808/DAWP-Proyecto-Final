package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Estilo;

public interface EstiloService {
    
    //GetEstilos: Devuelve una lista con todos los estilos
    public List<Estilo> getEstilos();

    //GetEstilosActivos: Devuelve una lista con todos los estilos activos
    public List<Estilo> getEstilosActivos(boolean activos);

    //GetEstilo: Devuelve un estilo por su ID
    public Estilo getEstilo(Estilo estilo);

    //Save: Guarda o actualiza un estilo existente
    public void save(Estilo estilo);

    //Delete: Elimina un estilo existente
    public void delete(Estilo estilo);

    //BuscarPorDescripcion: Busca una estilo por su descripcion
    public List<Estilo> buscarPorDescripcion(String descripcion);

}
