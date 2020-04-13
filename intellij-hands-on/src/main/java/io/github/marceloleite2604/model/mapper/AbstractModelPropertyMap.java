package io.github.marceloleite2604.model.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public abstract class AbstractModelPropertyMap<PO, DTO> {

    private PropertyMap<PO, DTO> poToDtoPropertyMap;

    private PropertyMap<DTO, PO> dtoToPoPropertyMap;

    public AbstractModelPropertyMap(PropertyMap<PO, DTO> poToDtoPropertyMap, PropertyMap<DTO, PO> dtoToPoPropertyMap) {
        this.poToDtoPropertyMap = poToDtoPropertyMap;
        this.dtoToPoPropertyMap = dtoToPoPropertyMap;
    }

    public void addPropertyMaps(ModelMapper modelMapper) {
        modelMapper.addMappings(poToDtoPropertyMap);
        modelMapper.addMappings(dtoToPoPropertyMap);
    }
}
