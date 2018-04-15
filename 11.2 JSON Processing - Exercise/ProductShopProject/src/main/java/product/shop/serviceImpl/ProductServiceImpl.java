package product.shop.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.shop.model.dtos.bindings.ProductCreateBindingModel;
import product.shop.model.dtos.views.ProductViewModel;
import product.shop.model.entity.Product;
import product.shop.repository.ProductRepository;
import product.shop.service.CategoryService;
import product.shop.service.ProductService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public void save(ProductCreateBindingModel model) {
        Product product = this.modelMapper.map(model, Product.class);
        Random random = new Random();
        product.getCategories().add(this.categoryService.findAll().get(random.nextInt(10)));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void save(Collection<ProductCreateBindingModel> models) {
        List<Product> products = models.stream().map(m -> this.modelMapper.map(m,Product.class)).collect(Collectors.toList());
        Random random = new Random();
        for (Product p:products) {
            p.getCategories().add(this.categoryService.findAll().get(random.nextInt(10)));
        }
        this.productRepository.saveAll(products);
    }

    @Override
    public List<ProductViewModel> getAllByRangeWithoutBuyer(BigDecimal from, BigDecimal to) {
        return this.productRepository.getAllByRangeWithoutBuyer(from, to);
    }

}
