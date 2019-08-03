package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter;

import java.util.Objects;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.github.marceloleite2604.tutorials.modelmapper.bo.AbstractBO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.DataTransferObject;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.PersistentObject;

public abstract class AbstractIdToPersistentObjectConverter<I, P extends PersistentObject<I>, K, D extends DataTransferObject<K>>
		implements Converter<K, P> {

	@Override
	public P convert(MappingContext<K, P> context) {
		K id = context.getSource();

		if (Objects.isNull(id)) {
			return null;
		}

		return getBO().findMandatoryById(convertId(id));
	}

	protected abstract AbstractBO<I, P, D> getBO();

	protected abstract I convertId(K id);
}
