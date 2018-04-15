package softuni.gamestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.models.entities.Role;
import softuni.gamestore.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(Roles role) {
        return this.roleRepository.getRoleByName(role.name());
    }
}
