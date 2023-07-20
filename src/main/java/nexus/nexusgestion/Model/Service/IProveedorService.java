package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Proveedor;


public interface IProveedorService {
    
    public List<Proveedor> buscarTodo();

    public List<Proveedor> buscarPor(String criterio);

    public Proveedor buscarPorId(Long id);
    
    public void guardar(Proveedor proveedores);

}
