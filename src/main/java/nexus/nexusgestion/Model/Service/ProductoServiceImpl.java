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

    @Transactional
    @Override
    public void intercambiarStockSucursales(Producto producto, String origenSucursal, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }

        int stockActualOrigen = 0;
        int stockActualDestino = 0;

        if ("sucursalUno".equals(origenSucursal)) {
            stockActualOrigen = producto.getStockSucursalUno();
            stockActualDestino = producto.getStockSucursalDos();
        } else if ("sucursalDos".equals(origenSucursal)) {
            stockActualOrigen = producto.getStockSucursalDos();
            stockActualDestino = producto.getStockSucursalUno();
        } else {
            throw new IllegalArgumentException("La sucursal de origen no es válida.");
        }

        if (stockActualOrigen >= cantidad) {
            // Actualizar el stock de la sucursal de origen
            stockActualOrigen -= cantidad;
            if ("sucursalUno".equals(origenSucursal)) {
                producto.setStockSucursalUno(stockActualOrigen);
            } else {
                producto.setStockSucursalDos(stockActualOrigen);
            }

            // Actualizar el stock de la sucursal de destino
            stockActualDestino += cantidad;
            if ("sucursalUno".equals(origenSucursal)) {
                producto.setStockSucursalDos(stockActualDestino);
            } else {
                producto.setStockSucursalUno(stockActualDestino);
            }
        } else {
            throw new IllegalStateException("No hay suficiente stock en la sucursal de origen.");
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
