package app.exam.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.ModelParser;

@Component
public class ModelParserImpl implements ModelParser {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelParserImpl() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.modelMapper.addMappings(propertyMap);
        return this.modelMapper.map(source, destinationClass);
    }
}
