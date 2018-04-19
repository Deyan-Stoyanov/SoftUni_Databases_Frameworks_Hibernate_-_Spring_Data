package car.dealer.service;

import car.dealer.model.dto.importDtos.SupplierImportDto;
import car.dealer.model.entity.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupplierService {
    void save(Supplier supplier);
    void save(List<Supplier> supplier);
    Optional<Supplier> getById(Long id);
}
