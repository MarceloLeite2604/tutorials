package io.github.marceloleite2604.model.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PoToDtoMapper<PO, DTO> {

    private final Class<? extends PO> poClass;

    private final Class<? extends DTO> dtoClass;

    private final ModelMapper modelMapper;

    public PoToDtoMapper(Class<? extends PO> poClass, Class<? extends DTO> dtoClass, ModelMapper modelMapper) {
        this.poClass = poClass;
        this.dtoClass = dtoClass;
        this.modelMapper = modelMapper;
    }

    public DTO mapToDto(PO po) {
        return modelMapper.map(po, dtoClass);
    }

    public List<DTO> mapAllToDto(List<PO> pos) {
        return pos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public PO mapToPo(DTO dto) {
        return modelMapper.map(dto, poClass);
    }

    public List<PO> mapAllToPo(List<DTO> dtos) {
        return dtos.stream().map(this::mapToPo).collect(Collectors.toList());
    }
}
