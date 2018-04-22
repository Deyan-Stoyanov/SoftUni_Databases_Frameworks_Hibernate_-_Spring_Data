package hiberspring.repositories;

import hiberspring.dtos.views.EmployeeProductiveViewDto;
import hiberspring.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT new hiberspring.dtos.views.EmployeeProductiveViewDto(concat(e.firstName, ' ', e.lastName), e.position, c.number ) FROM Employee AS e JOIN e.card AS c JOIN e.branch AS b WHERE b.id IN (SELECT p.branch.id FROM Product AS p) ORDER BY e.firstName ASC, e.lastName ASC, length(e.position) DESC ")
    List<EmployeeProductiveViewDto> getAllEmployeesFromBranchesWithProducts();

}
