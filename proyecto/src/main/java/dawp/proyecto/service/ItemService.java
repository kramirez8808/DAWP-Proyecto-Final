package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.ArrayList;
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Item;

public interface ItemService {
    
    // Se crea una lista para almacenar los items del carrito
    List<Item> listaItems = new ArrayList<>();
    
    //Se recuperan todos los items
    public List<Item> gets();
    
    //Se recupera el registro que tiene el idItem pasado por parámetro
    //si no existe en la tabla se retorna null
    public Item get(Item item);
    
    //Se elimina el registro que tiene el idItem pasado por parámetro
    public void delete(Item item);
    
    //Si el objeto item tiene un idItem que existe en la tabla item
    //El registro de actualiza con la nueva información
    //Si el idItem NO existe en la tabla, se crea el registro con esa información
    public void save(Item item);
    
    //Se actualiza el item en el carrito
    public void actualiza(Item item);
    
    // Se facturan los items del carrito y se eliminan
    public void facturar();

}
