package photography.workshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.workshop.io.validator.ValidationUtil;
import photography.workshop.model.entity.Photographer;
import photography.workshop.repositories.PhotographerRepository;
import photography.workshop.services.PhotographerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {
    private final PhotographerRepository photographerRepository;

    @Autowired
    public PhotographerServiceImpl(PhotographerRepository photographerRepository) {
        this.photographerRepository = photographerRepository;
    }

    @Override
    public List<Photographer> getAll() {
        return this.photographerRepository.findAll();
    }

    @Override
    public void save(Photographer photographer) {
        if(ValidationUtil.isValid(photographer)){
            this.photographerRepository.saveAndFlush(photographer);
        }
    }

    @Override
    public void save(List<Photographer> photographers) {

        for (Photographer p:photographers) {
            if(ValidationUtil.isValid(p)){
                this.photographerRepository.saveAndFlush(p);
            }
        }
    }
}
