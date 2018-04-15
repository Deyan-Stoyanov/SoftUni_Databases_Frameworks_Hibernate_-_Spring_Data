package softuni.gamestore.services;

import org.springframework.stereotype.Service;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.models.entities.User;

import java.util.Set;

@Service
public interface UserService {
    Long register(UserRegisterBindingModel model);
    User findUserById(Long id);
    Set<GameBindingModel> findOwnedGames(Long id);
}
