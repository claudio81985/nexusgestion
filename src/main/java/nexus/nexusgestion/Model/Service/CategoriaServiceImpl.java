package nexus.nexusgestion.Model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nexus.nexusgestion.Model.Entities.Categoria;
import nexus.nexusgestion.Model.Repository.ICategoriaRepository;


@Service
public class CategoriaServiceImpl implements ICategoriaService{

    @Autowired
    ICategoriaRepository categoriaRespository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarTodo() {
        
        return categoriaRespository.findAll();
    }

    @Override
    public List<Categoria> buscarPor(String criterio) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
       
        return categoriaRespository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaRespository.save(categoria);
        
    }

    
}