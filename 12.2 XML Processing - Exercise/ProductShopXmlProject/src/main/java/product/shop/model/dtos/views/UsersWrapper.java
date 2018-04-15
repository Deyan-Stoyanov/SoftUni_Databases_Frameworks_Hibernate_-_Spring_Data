package product.shop.model.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersWrapper {

    @XmlElement(name = "user")
    private List<UserViewModel> users;

    public UsersWrapper(List<UserViewModel> users) {
        this.users = users;
    }

    public UsersWrapper() {
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }
}
