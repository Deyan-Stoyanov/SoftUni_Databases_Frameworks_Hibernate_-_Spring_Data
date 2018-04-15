package softuni.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.gamestore.models.entities.Role;
import softuni.gamestore.services.RoleService;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String role);
}
