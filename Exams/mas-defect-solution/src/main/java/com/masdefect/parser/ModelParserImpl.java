package com.masdefect.parser;

import com.masdefect.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelParserImpl implements ModelParser {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        modelMapper.addMappings(propertyMap);
        return modelMapper.map(source, destinationClass);
    }
}
