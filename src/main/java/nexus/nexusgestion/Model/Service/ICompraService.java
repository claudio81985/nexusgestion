package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Compra;

public interface ICompraService {

    public List<Compra> buscarTodo();

    public Compra buscarPorId(Long id);

    public List<Compra> buscarPor(String criterio);
    
    public void guardar(Compra compra);

    public Long obtenerUltimoIdCompra();
    
}
