package product.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import product.shop.model.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    @Query(value = "SELECT u FROM User AS u JOIN Product AS p ON u.id=p.seller.id WHERE p.seller.id=u.id")
    List<User> getAllBySoldItems();
}
