package photography.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photography.workshop.model.entity.Lens;

@Repository
public interface LensRepository extends JpaRepository<Lens, Long> {
}
