package car.dealer.repository;

import car.dealer.model.dto.SupplierDto;
import car.dealer.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Part getById(Long id);
    @Query(value = "SELECT new car.dealer.model.dto.SupplierDto(p.supplier.id, p.supplier.name, count(p.id)) FROM Part AS p JOIN p.supplier AS s WHERE s.isImporter=false GROUP BY s")
    List<SupplierDto> localSuppliers();
}
