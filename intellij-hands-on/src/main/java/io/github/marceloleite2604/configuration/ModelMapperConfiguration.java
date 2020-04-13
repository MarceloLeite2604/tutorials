package io.github.marceloleite2604.configuration;

import io.github.marceloleite2604.model.mapper.person.property.PersonPropertyMap;
import io.github.marceloleite2604.model.mapper.skill.property.SkillPropertyMap;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean(BeanNames.MODEL_MAPPER)
    public ModelMapper createModelMapper(PersonPropertyMap personPropertyMap, SkillPropertyMap skillPropertyMap) {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        personPropertyMap.addPropertyMaps(modelMapper);
        skillPropertyMap.addPropertyMaps(modelMapper);

        modelMapper.validate();

        return modelMapper;
    }
}
