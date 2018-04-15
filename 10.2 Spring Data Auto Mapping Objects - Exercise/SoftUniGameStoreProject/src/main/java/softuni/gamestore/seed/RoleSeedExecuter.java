package softuni.gamestore.seed;

import org.springframework.stereotype.Component;
import softuni.gamestore.models.entities.Role;
import softuni.gamestore.repositories.RoleRepository;

import javax.annotation.PostConstruct;

@Component
public class RoleSeedExecuter {
    private final RoleRepository roleRepository;

    public RoleSeedExecuter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void insertRole(){
        if(this.roleRepository.count()==0L){
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Role userRole = new Role();
            userRole.setName("USER");
            this.roleRepository.saveAndFlush(adminRole);
            this.roleRepository.saveAndFlush(userRole);
        }
    }
}
