package nexus.nexusgestion.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import nexus.nexusgestion.Model.Entities.Sucursal;

public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {

    @Query("select s from Sucursal s Where s.nombre like %:criterio%  and s.activo = true")
    List<Sucursal> buscarPor(@Param("criterio") String criterio );

    @Query("select s from Sucursal s Where s.activo = true")
    List<Sucursal> buscarSoloHabilitados( );
    
}
