package product.shop.model.dtos.views;

import com.google.gson.annotations.Expose;
import product.shop.model.entity.User;

import java.util.List;

public class UserWrapper {
    @Expose
    private Integer count;
    @Expose
    private List<UserInfoView> users;

    public UserWrapper(Integer count, List<UserInfoView> users) {
        this.count = count;
        this.users = users;
    }

    public UserWrapper() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserInfoView> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfoView> users) {
        this.users = users;
    }
}
