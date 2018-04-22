package app.exam.repository;

import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query(value = "SELECT new app.exam.domain.dto.json.ItemJSONExportDTO(i.name, i.price, oi.quantity) FROM OrderItem AS oi JOIN oi.item AS i JOIN oi.order AS o WHERE o.id=:order")
    List<ItemJSONExportDTO> getItemDtosByOrderNumber(@Param("order") Integer order);

    @Query("select new app.exam.domain.dto.xml.MostPopularItemDTO(i.name, (i.price * (sum(o.quantity))), sum(o.quantity)) from OrderItem AS o JOIN o.item AS i JOIN i.category AS c WHERE c.id=:category group by i.id ORDER BY (i.price * (sum(o.quantity))) DESC")
    List<MostPopularItemDTO> mostPopularItemsByCategoryId(@Param("category") Integer category);

}
