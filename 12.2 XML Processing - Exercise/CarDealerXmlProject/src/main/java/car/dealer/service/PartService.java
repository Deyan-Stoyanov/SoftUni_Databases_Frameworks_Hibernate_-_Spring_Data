package car.dealer.service;

import car.dealer.model.dto.exportDtos.SupplierDto;
import car.dealer.model.entity.Part;

import java.util.List;
import java.util.Optional;

public interface PartService {
    void save(Part part);
    void save(List<Part> parts);
    Optional<Part> getById(Long id);
    List<Part> getAllParts();
    List<SupplierDto> localSuppliers();
}
