package nexus.nexusgestion.Model.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nexus.nexusgestion.Model.Entities.Producto;
import nexus.nexusgestion.Model.Repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

    // Inyeccion de Dependencias (dependece Injection - DI)
    @Autowired
    IProductoRepository productoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarTodo() {

        return productoRepo.buscarSoloHabilitados();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarPor(String criterio) {

        return productoRepo.buscarPor(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {

        return productoRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void aumentarPreciosPorProveedor(Long proveedorId, BigDecimal aumentoPorcentaje) {
        List<Producto> productos = productoRepo.findByProveedorId(proveedorId);

        if (productos.isEmpty()) {
            throw new ProveedorSinProductosException("El proveedor no tiene productos asignados.");
        }

        for (Producto producto : productos) {
            BigDecimal aumento = producto.getPrecio().multiply(aumentoPorcentaje.divide(new BigDecimal(100)));
            producto.setPrecio(producto.getPrecio().add(aumento));
        }

        productoRepo.saveAll(productos);
    }

    @Override
    public void intercambiarStockSucursales(Producto producto, int cantidadDesde, int cantidadHacia) {
        if (cantidadDesde < 0 || cantidadHacia < 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva.");
        }
    
        if (cantidadDesde > 0) {
            int stockActualSucursalUno = producto.getStockSucursalUno();
            int stockActualSucursalDos = producto.getStockSucursalDos();
    
            if (stockActualSucursalUno >= cantidadDesde) {
                producto.setStockSucursalUno(stockActualSucursalUno - cantidadDesde);
                producto.setStockSucursalDos(stockActualSucursalDos + cantidadDesde);
            } else {
                throw new IllegalStateException("No hay suficiente stock en la Sucursal Uno.");
            }
        }
    
        if (cantidadHacia > 0) {
            int stockActualSucursalUno = producto.getStockSucursalUno();
            int stockActualSucursalDos = producto.getStockSucursalDos();
    
            if (stockActualSucursalDos >= cantidadHacia) {
                producto.setStockSucursalUno(stockActualSucursalUno + cantidadHacia);
                producto.setStockSucursalDos(stockActualSucursalDos - cantidadHacia);
            } else {
                throw new IllegalStateException("No hay suficiente stock en la Sucursal Dos.");
            }
        }
    }
    
    @Override
    public Producto buscarPorCodigoIdentificacion(String codigoIdentificacion) {
        List<Producto> productos = productoRepo.findByCodigoIdentificacion(codigoIdentificacion);

        if (!productos.isEmpty()) {
            return productos.get(0); // Devuelve el primer producto encontrado
        } else {
            return null; // No se encontró ningún producto con ese código de identificación
        }
    }

}
