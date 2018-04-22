package photography.workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import photography.workshop.model.entity.BasicCamera;

@Repository
public interface CameraRepository extends JpaRepository<BasicCamera, Long> {
    @Query(value = "SELECT c FROM BasicCamera AS c WHERE c.id=:id")
    BasicCamera getBasicCameraById(@Param("id")Long id);
}
