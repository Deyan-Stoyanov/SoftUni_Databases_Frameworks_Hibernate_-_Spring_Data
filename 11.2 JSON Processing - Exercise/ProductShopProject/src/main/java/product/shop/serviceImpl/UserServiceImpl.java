package product.shop.serviceImpl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.shop.model.dtos.bindings.UserCreateBindingModel;
import product.shop.model.dtos.views.*;
import product.shop.model.entity.Product;
import product.shop.model.entity.User;
import product.shop.repository.UserRepository;
import product.shop.service.UserService;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void save(UserCreateBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void save(Collection<UserCreateBindingModel> models) {
        Type listType = new TypeToken<List<User>>(){}.getType();
        Collection<User> users = this.modelMapper.map(models, listType);
        this.userRepository.saveAll(users);
    }

    @Override
    public List<UserViewModel> getAllBySoldItems() {
        List<User> users = this.userRepository.getAllBySoldItems();
        Type userListType = new TypeToken<List<UserViewModel>>(){}.getType();
        Type soldItemListType = new TypeToken<List<SoldProduct>>(){}.getType();
        List<UserViewModel> models = new ArrayList<>();
        for (User u:users) {
            if(u.getProductsForSale().stream().anyMatch(x -> x.getBuyer() != null)){
                UserViewModel uvm = this.modelMapper.map(u, UserViewModel.class);
                List<SoldProduct> products = this.modelMapper.map(u.getProductsForSale().stream().filter(x -> x.getBuyer() != null).collect(Collectors.toList()), soldItemListType);
                uvm.setSoldProducts(products);
                models.add(uvm);
            }
        }
        return models;
    }

    @Override
    public UserWrapper getAllUserProductData() {
        List<User> users = this.userRepository.getAllBySoldItems();
        List<UserInfoView> userInfoViews = new ArrayList<>();
        for (User u:users) {
            UserInfoView userView = new UserInfoView();
            userView.setFirstName(u.getFirstName());
            userView.setLastName(u.getLastName());
            userView.setAge(u.getAge());
            userView.setProductsCount((int) u.getProductsForSale().stream().filter(x -> x.getBuyer() != null).count());
            List<ProductInfoView> productInfoViews = new ArrayList<>();
            List<Product> validProducts = u.getProductsForSale().stream().filter(x -> x.getBuyer() != null).collect(Collectors.toList());
            for (Product p:validProducts) {
                ProductInfoView productInfoView = new ProductInfoView();
                productInfoView.setName(p.getName());
                productInfoView.setPrice(p.getPrice());
                productInfoViews.add(productInfoView);
            }
            userView.setProducts(productInfoViews);
            userInfoViews.add(userView);
        }
        UserWrapper wrapper = new UserWrapper(userInfoViews.size(), userInfoViews);
        return wrapper;
    }
}
