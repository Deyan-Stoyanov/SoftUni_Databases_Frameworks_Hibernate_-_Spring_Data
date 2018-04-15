package softuni.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.dto.binding.GameBindingModel;
import softuni.gamestore.models.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.models.entities.Game;
import softuni.gamestore.models.entities.User;
import softuni.gamestore.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public Long register(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        user.setGames(new HashSet<>());
        this.setUserRole(user);
        if(model.isPasswordMatched()){
            user = this.userRepository.saveAndFlush(user);
        }
        return user.getId();
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public Set<GameBindingModel> findOwnedGames(Long id) {
        User user = this.userRepository.getOne(id);
        Set<Game> allGames = user.getGames();
        Set<GameBindingModel> ownedGames = new HashSet<>();
        for (Game g:allGames) {
            ownedGames.add(this.modelMapper.map(g, GameBindingModel.class));
        }
        return ownedGames;
    }

    private void setUserRole(User user){
        if(this.userRepository.count() > 0){
            user.getRoles().add(this.roleService.getRoleByName(RoleService.Roles.USER));
        } else {
            user.getRoles().add(this.roleService.getRoleByName(RoleService.Roles.ADMIN));
        }
    }
}
