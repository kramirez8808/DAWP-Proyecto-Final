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
    
    //Retorna el item que tiene el idItem pasado como parámetro
    public Item get(Item item);
    
    //Se elimina un producto del carrito
    public void delete(Item item);
    
    //Se añade o se actualiza un producto al carrito en caso de que exista
    public void save(Item item);
    
    //Se actualiza la cantidad de un item en el carrito
    public void actualiza(Item item);
    
    // Se facturan los items del carrito y se eliminan
    public void facturar();

}
