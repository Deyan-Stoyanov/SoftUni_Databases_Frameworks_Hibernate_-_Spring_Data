package photography.workshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.workshop.model.entity.Accessory;
import photography.workshop.repositories.AccessoryRepository;
import photography.workshop.services.AccessoryService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {
    private final AccessoryRepository accessoryRepository;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    @Override
    public List<Accessory> getAll() {
        return this.accessoryRepository.findAll();
    }

    @Override
    public void save(Accessory accessory) {
        this.accessoryRepository.saveAndFlush(accessory);
    }

    @Override
    public void save(List<Accessory> accessories) {
        this.accessoryRepository.saveAll(accessories);
    }
}
