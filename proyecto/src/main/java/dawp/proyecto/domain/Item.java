package dawp.proyecto.domain;

// ------ EXTERNAL IMPORTS ------
import lombok.Data;
import lombok.EqualsAndHashCode;

// ------ INTERNAL IMPORTS ------

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto {

    //Se añade el atributo para la cantidad de un mismo producto en el carrito
    private int cantidad;

    //Constructores
    public Item() {
    }

    public Item(Producto producto) {
        super.setIdProducto(producto.getIdProducto());
        super.setCategoria(producto.getCategoria());
        super.setDescripcion(producto.getDescripcion());
        super.setDetalle(producto.getDetalle());
        super.setPrecio(producto.getPrecio());
        super.setExistencias(producto.getExistencias());
        super.setActivo(producto.isActivo());
        super.setImagen(producto.getImagen());
        this.cantidad = 0;
    }
}
