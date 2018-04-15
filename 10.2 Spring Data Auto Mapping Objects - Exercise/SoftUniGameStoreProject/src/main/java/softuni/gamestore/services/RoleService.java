package softuni.gamestore.services;

import org.springframework.stereotype.Service;
import softuni.gamestore.models.entities.Role;

@Service
public interface RoleService {

    Role getRoleByName(Roles role);

    enum Roles{
        ADMIN, USER
    }
}
