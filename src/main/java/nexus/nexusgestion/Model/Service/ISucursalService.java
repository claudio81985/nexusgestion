package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Sucursal;


public interface ISucursalService {

    public void guardar (Sucursal sucursal);
    
    public List<Sucursal> buscarTodos();

    public List<Sucursal> buscarPor(String criterio);

    public Sucursal buscarPorId(Long id);

   
    
}
