package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Permiso;

public interface IPermisoService {
    
    public List<Permiso> buscarTodo();

    public List<Permiso> buscarPor(String criterio);

    public Permiso buscarPorId(Long id);
    
    public void guardar(Permiso permiso);
}
