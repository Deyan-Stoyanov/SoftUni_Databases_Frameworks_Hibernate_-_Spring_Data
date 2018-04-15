package product.shop.utils.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import product.shop.model.dtos.bindings.ProductCreateBindingModel;
import product.shop.model.entity.Product;
import product.shop.repository.UserRepository;

@Component
public class ModelMapperConfig {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ModelMapperConfig(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.initialize();
    }

    private void initialize(){
        this.productCreateBindingConfiguration();
    }

    private void productCreateBindingConfiguration() {
        Converter<ProductCreateBindingModel, Product> con = new AbstractConverter<ProductCreateBindingModel, Product>() {
            @Override
            protected Product convert(ProductCreateBindingModel src) {
                Product p = new Product();
                Integer buyer = src.getBuyer();
                if (buyer != null) {
                    p.setBuyer(userRepository.findAll().get(buyer));
                }
                p.setSeller(userRepository.findAll().get(src.getSeller()));
                p.setName(src.getName());
                p.setPrice(src.getPrice());
                return p;
            }
        };
        this.modelMapper.addConverter(con);
    }
}