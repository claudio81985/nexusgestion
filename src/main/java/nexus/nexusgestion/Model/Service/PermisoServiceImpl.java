package nexus.nexusgestion.Model.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import nexus.nexusgestion.Model.Entities.Permiso;
import nexus.nexusgestion.Model.Repository.IPermisoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermisoServiceImpl implements IPermisoService {
    
    @Autowired
    IPermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Permiso> buscarTodo() {

        return permisoRepository.findAll();
    }

    @Override
    public List<Permiso> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    @Transactional(readOnly = true)
    public Permiso buscarPorId(Long id) {
        
        return permisoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Permiso permiso) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }
    
}
