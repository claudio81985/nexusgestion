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
        System.out.println("Productos con igual proveedor encontrados = " + productos);

        if (productos.isEmpty()) {
            throw new ProveedorSinProductosException("El proveedor no tiene productos asignados.");
        }

        for (Producto producto : productos) {
            System.out.println("Producto = " + producto);
            BigDecimal aumento = producto.getPrecio().multiply(aumentoPorcentaje.divide(new BigDecimal(100)));
            System.out.println("Aumento = " + aumento);
            producto.setPrecio(producto.getPrecio().add(aumento));
            System.out.println("Producto = " + producto);
            System.out.println("Producto precio = " + producto.getPrecio());
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

    @Override
    public String obtenerUltimoCodigo() {
        List<Producto> listaProductos = productoRepo.findAll();
    
        // Verifica si la lista de productos no está vacía
        if (!listaProductos.isEmpty()) {
            // Ordena la lista de productos por ID de forma descendente
            listaProductos.sort((p1, p2) -> p2.getId().compareTo(p1.getId()));
    
            // Obtén el primer producto de la lista (que tiene el ID más alto)
            Producto ultimoProducto = listaProductos.get(0);

            System.out.println("Último producto con ID mayor: " + ultimoProducto);
    
            // Obtén el codigoIdentificacion del último producto y devuélvelo
            return ultimoProducto.getCodigoIdentificacion();
        }
    
        // Si la lista de productos está vacía, puedes manejarlo de acuerdo a tus necesidades
        // Puedes devolver un valor predeterminado, lanzar una excepción, etc.
        return "PAISA-1"; // Por ejemplo, si no hay productos, se devuelve "PAISA-1"
    }    

}
