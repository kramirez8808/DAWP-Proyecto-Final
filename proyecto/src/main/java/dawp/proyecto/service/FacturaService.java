package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Factura;

public interface FacturaService {
    
    //GetFacturas: Devuelve una lista con todas las facturas
    public List<Factura> getFacturas();

    //GetFacturasActivas: Devuelve una lista con todas las facturas activas
    public List<Factura> getFacturasActivas(boolean activos);

    //GetFactura: Devuelve una factura por su ID
    public Factura getFactura(Factura factura);

    //SaveFactura: Guarda o actualiza una factura existente
    public void saveFactura(Factura factura);

    //DeleteFactura: Elimina una factura existente
    public void deleteFactura(Factura factura);
}
