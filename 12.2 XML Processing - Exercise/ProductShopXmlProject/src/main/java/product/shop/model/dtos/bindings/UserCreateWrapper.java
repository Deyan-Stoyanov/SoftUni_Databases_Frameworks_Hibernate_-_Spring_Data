package product.shop.model.dtos.bindings;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserCreateWrapper {
    @XmlElement(name = "user")
    private List<UserCreateBindingModel> userCreateBindingModels;

    public UserCreateWrapper(List<UserCreateBindingModel> userCreateBindingModels) {
        this.userCreateBindingModels = userCreateBindingModels;
    }

    public UserCreateWrapper() {
    }

    public List<UserCreateBindingModel> getUserCreateBindingModels() {
        return userCreateBindingModels;
    }

    public void setUserCreateBindingModels(List<UserCreateBindingModel> userCreateBindingModels) {
        this.userCreateBindingModels = userCreateBindingModels;
    }
}
