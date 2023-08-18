package nexus.nexusgestion.Model.Service;

import java.math.BigDecimal;
import java.util.List;

import nexus.nexusgestion.Model.Entities.Producto;


public interface IProductoService {
    
    public List<Producto> buscarTodo();

    public List<Producto> buscarPor(String criterio);

    public Producto buscarPorId(Long id);

    public void guardar (Producto producto);

    public void aumentarPreciosPorProveedor(Long proveedorId, BigDecimal aumentoPorcentaje);
    
}
