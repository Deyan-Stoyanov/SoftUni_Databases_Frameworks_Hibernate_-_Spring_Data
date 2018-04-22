package hiberspring.repositories;

import hiberspring.dtos.views.branch.BranchViewDto;
import hiberspring.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch getBranchByName(String name);

    @Query(value = "select new hiberspring.dtos.views.branch.BranchViewDto(b.name, t.name, sum(p.clients)) FROM Branch AS b LEFT JOIN b.town AS t LEFT JOIN b.products AS p GROUP BY b.id ORDER BY sum(p.clients) DESC")
    List<BranchViewDto> getBranchesBySumOfClients();
}
