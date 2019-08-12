package com.github.marceloleite2604.tutorials.modelmapper.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import com.github.marceloleite2604.tutorials.modelmapper.model.dto.DataTransferObject;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.PersistentObject;

public class PoToDtoMapper<P extends PersistentObject<?>, D extends DataTransferObject<?>> {

	private Class<P> poClass;

	private Class<D> dtoClass;

	private ModelMapper modelMapper;

	public PoToDtoMapper(ModelMapper modelMapper, Class<P> poClass, Class<D> dtoClass) {
		this.modelMapper = modelMapper;
		this.poClass = poClass;
		this.dtoClass = dtoClass;
	}

	public P mapAsPo(D dto) {
		return modelMapper.map(dto, poClass);
	}

	public List<P> mapAsPo(List<D> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return Collections.emptyList();
		}

		List<P> pos = new ArrayList<>(dtos.size());

		dtos.stream()
				.map(this::mapAsPo)
				.forEach(pos::add);

		return pos;
	}

	public D mapAsDto(P po) {
		return modelMapper.map(po, dtoClass);
	}

	public List<D> mapAsDto(List<P> pos) {
		if (CollectionUtils.isEmpty(pos)) {
			return Collections.emptyList();
		}

		List<D> dtos = new ArrayList<>(pos.size());

		pos.stream()
				.map(this::mapAsDto)
				.forEach(dtos::add);

		return dtos;
	}
}
