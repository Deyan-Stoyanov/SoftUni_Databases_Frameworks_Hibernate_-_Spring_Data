package photography.workshop.io.modelMapping;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import photography.workshop.model.dto.binding.CameraBindingModel;
import photography.workshop.model.entity.MirrorlessCamera;

@Component
public class ModelMapperConfig {
    private final ModelMapper modelMapper;

    public ModelMapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize(){

    }


}