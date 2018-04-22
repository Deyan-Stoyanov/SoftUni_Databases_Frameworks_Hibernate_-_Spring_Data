package photography.workshop.services;

import photography.workshop.model.entity.Lens;

import java.util.List;
import java.util.Optional;

public interface LensService {
    List<Lens> getAll();
    void save(Lens lens);
    void save(List<Lens> lenses);
    Optional<Lens> getById(Long id);
}
