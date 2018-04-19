package car.dealer.service;

import car.dealer.model.dto.exportDtos.SupplierDto;
import car.dealer.model.entity.Part;
import car.dealer.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    private final SupplierService supplierService;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(SupplierService supplierService, PartRepository partRepository) {
        this.supplierService = supplierService;
        this.partRepository = partRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void save(Part part) {
        this.partRepository.saveAndFlush(part);
    }

    @Override
    public void save(List<Part> parts) {

        this.partRepository.saveAll(parts);
    }

    @Override
    public Optional<Part> getById(Long id) {
        return this.partRepository.findById(id);
    }

    @Override
    public List<Part> getAllParts() {
        return this.partRepository.findAll();
    }

    @Override
    public List<SupplierDto> localSuppliers() {
        return this.partRepository.localSuppliers();
    }
}
