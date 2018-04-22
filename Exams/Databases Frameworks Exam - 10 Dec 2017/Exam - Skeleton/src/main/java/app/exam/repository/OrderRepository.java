package app.exam.repository;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select o FROM Order as o JOIN o.employee AS e WHERE e.name=:name AND o.type=:type")
    List<Order> exportOrdersByEmployeeAndOrderType(@Param(value = "name") String name, @Param(value = "type") OrderType type);
}
