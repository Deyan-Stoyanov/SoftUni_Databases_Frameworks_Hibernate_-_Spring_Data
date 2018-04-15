package product.shop.model.dtos.views;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersDataWrapper {

    @XmlElement(name = "user")
    private List<UserDataViewModel> users;

    @XmlAttribute
    private int count;

    public UsersDataWrapper(List<UserDataViewModel> users) {
        this.users = users;
        this.count = users.size();
    }

    public UsersDataWrapper() {
    }

    public List<UserDataViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserDataViewModel> users) {
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
