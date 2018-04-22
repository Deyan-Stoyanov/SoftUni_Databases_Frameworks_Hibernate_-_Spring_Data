package photography.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photography.workshop.model.entity.Photographer;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
}
