package softuni.gamestore.config;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperConfig {
    private final ModelMapper modelMapper;

    public ModelMapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize(){

    }
}
