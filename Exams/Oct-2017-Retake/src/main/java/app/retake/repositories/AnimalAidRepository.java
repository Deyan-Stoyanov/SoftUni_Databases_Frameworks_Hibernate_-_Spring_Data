package app.retake.repositories;

import app.retake.domain.dto.ProcedureAnimalAidXMLExportDTO;
import app.retake.domain.models.AnimalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid, Integer> {
    AnimalAid findByName(String name);

    @Query(value = "SELECT new app.retake.domain.dto.ProcedureAnimalAidXMLExportDTO(a.name, a.price) FROM AnimalAid AS a  WHERE a.id IN (SELECT s.id FROM Procedure AS p JOIN p.services AS s WHERE p.id=:id) ORDER BY a.price DESC ")
    List<ProcedureAnimalAidXMLExportDTO> getAllAnimalAidsByProcedureId(@Param("id") Integer id);
}
