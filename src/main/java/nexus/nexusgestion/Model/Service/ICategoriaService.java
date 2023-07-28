package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Categoria;

public interface ICategoriaService {
    
    public List<Categoria> buscarTodo();

    public List<Categoria> buscarPor(String criterio);

    public Categoria buscarPorId(Long id);
    
    public void guardar(Categoria categoria);
}
