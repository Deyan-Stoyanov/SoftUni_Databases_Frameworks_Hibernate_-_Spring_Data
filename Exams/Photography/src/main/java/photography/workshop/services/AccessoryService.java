package photography.workshop.services;

import photography.workshop.model.entity.Accessory;

import java.util.List;

public interface AccessoryService {
    List<Accessory> getAll();
    void save(Accessory accessory);
    void save(List<Accessory> accessories);
}
