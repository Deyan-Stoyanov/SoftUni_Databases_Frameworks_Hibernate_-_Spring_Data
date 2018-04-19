package car.dealer.service;

import car.dealer.model.dto.importDtos.SupplierImportDto;
import car.dealer.model.entity.Supplier;
import car.dealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void save(Supplier supplier) {
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void save(List<Supplier> supplier) {
        this.supplierRepository.saveAll(supplier);
    }

    @Override
    public Optional<Supplier> getById(Long id) {
        return this.supplierRepository.findById(id);
    }
}
