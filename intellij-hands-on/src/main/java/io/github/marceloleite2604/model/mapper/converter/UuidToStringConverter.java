package io.github.marceloleite2604.model.mapper.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UuidToStringConverter implements Converter<UUID, String> {

    @Override
    public String convert(MappingContext<UUID, String> mappingContext) {

        UUID source = mappingContext.getSource();

        if (Objects.isNull(source)) {
            return null;
        }

        return source.toString();
    }
}
