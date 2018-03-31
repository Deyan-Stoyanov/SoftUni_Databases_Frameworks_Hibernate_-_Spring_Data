package usersystem.usersystemproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usersystem.usersystemproject.models.entites.User;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> getAllByEmailEndsWith(String provider);
    @Modifying
    @Query(value = "UPDATE User AS u SET u.isDeleted=true WHERE u.lastTimeLoggedIn>:date")
    Integer setUserDeleted(@Param("date") Date date);
    @Modifying
    @Transactional
    @Query(value = "UPDATE User AS u SET u.isDeleted=true WHERE u.lastTimeLoggedIn>:date")
    void setUserIsDeleted(@Param("date") Date date);
    @Modifying
    void deleteAllByIsDeletedEquals(boolean isDeleted);
}
