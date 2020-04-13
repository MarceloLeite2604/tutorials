package io.github.marceloleite2604.model.mapper.converter;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToUuidConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(MappingContext<String, UUID> context) {
        String string = context.getSource();

        if (StringUtils.isBlank(string)) {
            return null;
        }

        return UUID.fromString(string);
    }
}
