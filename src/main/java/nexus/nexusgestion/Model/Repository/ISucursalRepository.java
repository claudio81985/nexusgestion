package nexus.nexusgestion.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nexus.nexusgestion.Model.Entities.Sucursal;

public interface ISucursalRepository extends JpaRepository<Sucursal, Long>{
    
}
