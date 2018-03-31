package usersystem.usersystemproject.services;

import org.springframework.data.repository.query.Param;
import usersystem.usersystemproject.models.entites.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    void saveUserToDb(User user);
    List<User> getAll();
    List<User> getAllByEmailEndsWith(String provider);
    Integer setUserDeleted(@Param("date") Date date);
    void setUserIsDeleted(@Param("date") Date date);
    void deleteAllByIsDeletedEquals(boolean isDeleted);
}
