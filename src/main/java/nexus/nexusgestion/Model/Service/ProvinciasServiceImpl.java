package nexus.nexusgestion.Model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nexus.nexusgestion.Model.Entities.Provincias;
import nexus.nexusgestion.Model.Repository.IProvinciasRepository;

@Service
public class ProvinciasServiceImpl implements IProvinciasService{

    @Autowired
    IProvinciasRepository provinciasRepository;


    @Override
    public List<Provincias> buscarTodo() {
        return provinciasRepository.findAll();
    }

    @Override
    public List<Provincias> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPor'");
    }

    @Override
    public Provincias buscarPorId(Long id) {
        return provinciasRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Provincias provincias) {
        provinciasRepository.save(provincias);
    }
    
}
