package product.shop.service;

import org.springframework.stereotype.Service;
import product.shop.model.dtos.bindings.UserCreateBindingModel;
import product.shop.model.dtos.views.UserDataViewModel;
import product.shop.model.dtos.views.UserViewModel;
import product.shop.model.dtos.views.UsersDataWrapper;
import product.shop.model.dtos.views.UsersWrapper;
import product.shop.model.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    List<User> findAll();
    void save(UserCreateBindingModel model);
    void save(Collection<UserCreateBindingModel> models);
    List<UserViewModel> getAllBySoldItems();
    UsersDataWrapper getUserData();

}
