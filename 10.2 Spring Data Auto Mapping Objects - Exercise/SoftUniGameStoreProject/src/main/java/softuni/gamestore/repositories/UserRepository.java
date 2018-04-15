package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.gamestore.models.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
