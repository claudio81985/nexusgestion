package nexus.nexusgestion.Model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nexus.nexusgestion.Model.Entities.Venta;
import nexus.nexusgestion.Model.Repository.IVentaRepositoy;

import javax.transaction.Transactional;

@Service
public class IVentaServiceImpl implements IVentaService {

 
    @Autowired
    IVentaRepositoy ventaRepository;

    @Override
    @Transactional
    public List<Venta> buscarTodo() {
        
        return ventaRepository.findAll();
    }

    @Override
    @Transactional
    public Venta buscarPorId(Long id) {
        
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Venta venta) {
        ventaRepository.save(venta);        
    }




    @Override
    public Long obtenerUltimoIdVenta() {
        return ventaRepository.findMaxId();
    }

}
