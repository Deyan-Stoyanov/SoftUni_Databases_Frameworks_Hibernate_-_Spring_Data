package photography.workshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.workshop.model.entity.Workshop;
import photography.workshop.repositories.WorkshopRepository;
import photography.workshop.services.WorkshopService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {
    private final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public List<Workshop> getAll() {
        return this.workshopRepository.findAll();
    }
}
