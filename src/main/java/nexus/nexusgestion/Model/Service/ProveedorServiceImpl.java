package nexus.nexusgestion.Model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nexus.nexusgestion.Model.Entities.Proveedor;
import nexus.nexusgestion.Model.Repository.IProveedorRepository;



@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    IProveedorRepository proveedorRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> buscarTodo() {
        return proveedorRepo.buscarSoloHabilitados();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> buscarPor(String criterio) {
        return proveedorRepo.buscarPor(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor buscarPorId(Long id) {
        return proveedorRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Proveedor proveedor) {
       proveedorRepo.save(proveedor);
    }

}
