package photography.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photography.workshop.model.entity.Accessory;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
}
