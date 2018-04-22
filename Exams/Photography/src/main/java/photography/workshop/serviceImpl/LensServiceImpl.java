package photography.workshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.workshop.model.entity.Lens;
import photography.workshop.repositories.LensRepository;
import photography.workshop.services.LensService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LensServiceImpl implements LensService {
    private final LensRepository lensRepository;

    @Autowired
    public LensServiceImpl(LensRepository lensRepository) {
        this.lensRepository = lensRepository;
    }

    @Override
    public List<Lens> getAll() {
        return this.lensRepository.findAll();
    }

    @Override
    public void save(Lens lens) {
        this.lensRepository.saveAndFlush(lens);
    }

    @Override
    public void save(List<Lens> lenses) {
        this.lensRepository.saveAll(lenses);
    }

    @Override
    public Optional<Lens> getById(Long id) {
        return this.lensRepository.findById(id);
    }
}
