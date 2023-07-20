package nexus.nexusgestion.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nexus.nexusgestion.Model.Entities.Stock;

public interface IStockRepository extends JpaRepository<Stock, Long> {

   
    
}
