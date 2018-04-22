package hiberspring.repositories;

import hiberspring.dtos.views.town.TownViewDto;
import hiberspring.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByName(String name);

    @Query(value = "SELECT new hiberspring.dtos.views.town.TownViewDto(t.name, t.population, (SELECT sum(p.clients) FROM Product AS p JOIN p.branch AS b WHERE b.town.id=t.id GROUP BY b.town.id)) FROM Town AS t ")
    List<TownViewDto> getTownsAndClientsCount();
}
