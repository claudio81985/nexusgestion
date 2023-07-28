package nexus.nexusgestion.Model.Service;

import java.util.List;
import nexus.nexusgestion.Model.Entities.Provincias;

public interface IProvinciasService {
    
    public List<Provincias> buscarTodo();

    public List<Provincias> buscarPor(String criterio);

    public Provincias buscarPorId(Long id);
    
    public void guardar(Provincias provincias);
}
