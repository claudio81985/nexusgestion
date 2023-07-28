package nexus.nexusgestion.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nexus.nexusgestion.Model.Entities.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Query("select p from Producto p Where p.codigoIdentificacion like %:criterio% or p.nombreComun like %:criterio% or p.nombreTecnico like %:criterio% or p.descripcion like %:criterio% and p.activo = true")
    List<Producto> buscarPor(@Param("criterio") String criterio);

    @Query("select p from Producto p Where p.activo = true")
    List<Producto> buscarSoloHabilitados();
}
