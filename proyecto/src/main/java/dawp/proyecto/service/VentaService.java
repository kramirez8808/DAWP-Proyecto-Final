package dawp.proyecto.service;

// ------ EXTERNAL IMPORTS ------
import java.util.List;

// ------ INTERNAL IMPORTS ------
import dawp.proyecto.domain.Venta;

public interface VentaService {

    //GetVentas: Devuelve una lista con todas las ventas
    public List<Venta> getVentas();

    //GetVentasActivas: Devuelve una lista con todas las ventas activas
    public List<Venta> getVentasActivas(boolean activos);

    //GetVenta: Devuelve una venta por su ID
    public Venta getVenta(Venta venta);

    //SaveVenta: Guarda o actualiza una venta existente
    public void saveVenta(Venta venta);

    //DeleteVenta: Elimina una venta existente
    public void deleteVenta(Venta venta);
    
}
