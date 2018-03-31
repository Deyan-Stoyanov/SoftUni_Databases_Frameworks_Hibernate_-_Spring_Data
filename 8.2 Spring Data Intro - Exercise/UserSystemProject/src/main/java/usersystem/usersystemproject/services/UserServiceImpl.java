package usersystem.usersystemproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.usersystemproject.models.entites.User;
import usersystem.usersystemproject.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUserToDb(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getAllByEmailEndsWith(String provider) {
        return this.userRepository.getAllByEmailEndsWith(provider);
    }

    @Override
    public Integer setUserDeleted(Date date) {
        return this.userRepository.setUserDeleted(date);
    }

    @Override
    public void setUserIsDeleted(Date date) {
        this.userRepository.setUserIsDeleted(date);
    }

    @Override
    public void deleteAllByIsDeletedEquals(boolean isDeleted) {
        this.userRepository.deleteAllByIsDeletedEquals(isDeleted);
    }
}
