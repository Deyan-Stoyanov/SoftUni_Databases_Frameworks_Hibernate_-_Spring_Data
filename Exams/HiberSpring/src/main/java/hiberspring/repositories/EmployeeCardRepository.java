package hiberspring.repositories;

import hiberspring.dtos.views.EmployeeCardUnusedViewDto;
import hiberspring.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {

    EmployeeCard getEmployeeCardByNumber(String number);

    @Query(value = "select new hiberspring.dtos.views.EmployeeCardUnusedViewDto(c.number) FROM EmployeeCard AS c WHERE c.id NOT IN (SELECT e.card.id FROM Employee AS e) ORDER BY c.id ASC ")
    List<EmployeeCardUnusedViewDto> getAllUnusedCards();
}
