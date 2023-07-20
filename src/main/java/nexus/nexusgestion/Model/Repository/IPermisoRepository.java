package nexus.nexusgestion.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nexus.nexusgestion.Model.Entities.Permiso;

public interface IPermisoRepository extends JpaRepository<Permiso, Long> {
    
}
