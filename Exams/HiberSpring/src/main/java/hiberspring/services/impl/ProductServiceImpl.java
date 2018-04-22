package hiberspring.services.impl;

import hiberspring.dtos.imports.product.ProductImportFromXmlDto;
import hiberspring.dtos.imports.product.ProductsImportFromXmlDto;
import hiberspring.entities.Branch;
import hiberspring.entities.Product;
import hiberspring.parser.ValidationUtil;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.ProductRepository;
import hiberspring.services.ProductService;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public void createOne(ProductsImportFromXmlDto productsImportFromXmlDto) {
        if (ValidationUtil.isValid(productsImportFromXmlDto)) {
            Product product = new Product();
            product.setName(productsImportFromXmlDto.getName());
            if (this.branchRepository.getBranchByName(productsImportFromXmlDto.getBranch()) != null) {
                product.setBranch(this.branchRepository.getBranchByName(productsImportFromXmlDto.getBranch()));
            }
            product.setClients(productsImportFromXmlDto.getClients());
            this.productRepository.save(product);
        } else {
            throw new IllegalArgumentException();
        }

    }

}
