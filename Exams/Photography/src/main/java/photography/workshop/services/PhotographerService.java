package photography.workshop.services;

import photography.workshop.model.entity.Photographer;

import java.util.List;

public interface PhotographerService {
    List<Photographer> getAll();
    void save(Photographer photographer);
    void save(List<Photographer> photographers);
}
