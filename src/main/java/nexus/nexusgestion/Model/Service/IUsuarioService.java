package nexus.nexusgestion.Model.Service;

import java.util.List;

import nexus.nexusgestion.Model.Entities.Usuario;


public interface IUsuarioService {

    public void guardar (Usuario usuario);
    
    public List<Usuario> buscarTodos();

    public List<Usuario> buscarPor(String criterio);

    public Usuario buscarPorId(Long id);

    public Usuario buscarPorNombre(String nombreUsuario);

    public Long obtenerSucursalAsignada();
}
