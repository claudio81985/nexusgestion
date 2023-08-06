package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Venta;


public interface IVentaService {
    
    public List<Venta> buscarTodo();

    public Venta buscarPorId(Long id);
    
    public void guardar(Venta venta);

    Long obtenerUltimoIdVenta();

    public Object buscarSoloHabilitados();
    
}
