package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Categoria;

public interface CategoriaService {
    
    //GetCategorias: Devuelve una lista con todos los categorias
    public List<Categoria> getCategorias();

    //GetCategoriasActivas: Devuelve una lista con todos los categorias activos
    public List<Categoria> getCategoriasActivas(boolean activos);

    //GetCategoria: Devuelve un categoria por su ID
    public Categoria getCategoria(Categoria categoria);
    
    //Save: Guarda o actualiza una categoria si esta existe o no
    public void save(Categoria categoria);
    
    //Delete: Elimina una categoria
    public void delete(Categoria categoria);

    //BuscarPorDescripcion: Busca una categoria por su descripcion
    public List<Categoria> buscarPorDescripcion(String descripcion);

}
