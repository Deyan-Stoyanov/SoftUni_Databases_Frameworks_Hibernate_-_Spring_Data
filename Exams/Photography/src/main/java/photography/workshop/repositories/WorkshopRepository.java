package photography.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photography.workshop.model.entity.Workshop;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
