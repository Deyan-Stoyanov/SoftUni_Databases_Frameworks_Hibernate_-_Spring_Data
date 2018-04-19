package car.dealer.repository;

import car.dealer.model.dto.exportDtos.CustomerViewModel;
import car.dealer.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query(value = "SELECT new car.dealer.model.dto.exportDtos.CustomerViewModel(c.name, count(s.id), sum (p.price)) FROM Sale as s LEFT JOIN s.customer AS c JOIN s.car.parts AS p GROUP BY c")
    List<CustomerViewModel> customersAndTotalSpentMoney();
}
