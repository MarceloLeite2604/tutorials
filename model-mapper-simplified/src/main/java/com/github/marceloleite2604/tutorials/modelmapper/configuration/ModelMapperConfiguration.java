package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.modelmapper.bo.UserBO;
import com.github.marceloleite2604.tutorials.modelmapper.model.dto.UserDTO;
import com.github.marceloleite2604.tutorials.modelmapper.model.po.UserPO;

@SuppressWarnings("squid:CommentedOutCodeLine")
@Configuration
public class ModelMapperConfiguration {

	@Inject
	private UserBO userBO;

	@Bean(BeanNames.MODEL_MAPPER)
	public ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setSkipNullEnabled(true);

		// createUserDtoToUserPoPropertyMap(modelMapper);
		createWrongUserDtoToUserPoPropertyMap(modelMapper);

		// After adding all type maps on ModelMapper, we request it to validate our
		// construction. If something is awkward or a property mapping is amiss,
		// ModelMapper will throw us an exception.
		modelMapper.validate();

		return modelMapper;
	}

	@SuppressWarnings("unused")
	private void createUserDtoToUserPoPropertyMap(ModelMapper modelMapper) {
		PropertyMap<UserDTO, UserPO> userDtoToUserPoPropertyMap = new PropertyMap<>() {

			@Override
			protected void configure() {

				// To create conditional mappings, we can create Condition objects which return
				// true when a mapping should be done or false when it should be skipped.
				// The following condition checks if a string is not null or blank.
				Condition<String, String> stringIsNotNullOrBlank = context -> StringUtils
						.isNotBlank(context.getSource());

				// When there is a complex mapping (e. g. convert a string to UUID or retrieve
				// an object from database) we can use Converters.
				// The following converter will create a new UUID object based on a string.
				Converter<String, UUID> stringToUuidConverter = context -> UUID
						.fromString(context.getSource());

				// Here we'll construct our mapping between UserDTO.id (a string field) and
				// UserPO.id (a UUID field).
				// The "when" method uses our "stringIsNotNull" to check if UserDTO.id is not
				// null. If the condition returns true our mapping will be done. False, it will
				// be skipped.
				// The "using" method indicates that we'll use our "stringToUuidConverter" to
				// convert a string object into an UUID object.
				// Finally, on "map" method, we'll define the source (UserDTO.id field) and our
				// destination (UserPO.id field).
				// Observation: When mapping fields, always use the "getter" methods.

				// @formatter:off
				when(stringIsNotNullOrBlank)
				.using(stringToUuidConverter)
				.map(source.getId(), destination.getId());
				// @formatter:on

				// Here is a more complex converter we'll use to fulfill the UserPO.password
				// field.
				Converter<UserDTO, String> passwordConverter = context -> {
					UserDTO user = context.getSource();
					// When UserDTO refers to a new user, we should pass UserDTO.password field to
					// UserPO.password.
					if (userBO.isNew(user)) {
						return user.getPassword();

						// When UserDTO refers to an existing user, we'll retrieve its information
						// from database and pass its current password.
					} else {
						UUID uuid = UUID.fromString(user.getId());
						UserPO userPO = userBO.findMandatoryById(uuid);
						return userPO.getPassword();
					}
				};

				// Using the converter created above, we'll define the UserPO.password mapping
				// rule.
				// Notice that, this time, we are mapping the whole "source" object on the map.
				// Since our "passwordConverter" requires the whole UserDTO object to work.
				using(passwordConverter).map(source, destination.getPassword());

				// If we do not want to map some fields, we can use the "skip" method.
				skip(source.isEnabled(), destination.isEnabled());
				// Note: if our source object does not have a corresponding field, you can use
				// the source object itself.
				// For example:
				// skip(source, destination.isEnabled());

				// An alternative way to skip is just use the "skip" method and then use the
				// UserPO setter with a null object (not very intuitive, though, since all other
				// ModelMapper methods are based on the getter method).
				skip().setDeleted(null);
			}
		};

		modelMapper.addMappings(userDtoToUserPoPropertyMap);
	}

	/**
	 * This method will create a really simplified UserDTO to UserPO mapper. It will
	 * only map the "id" field.
	 */
	@SuppressWarnings("unused")
	private void createWrongUserDtoToUserPoPropertyMap(ModelMapper modelMapper) {
		PropertyMap<UserDTO, UserPO> userDtoToUserPoPropertyMap = new PropertyMap<>() {

			@Override
			protected void configure() {

				Condition<String, String> stringIsNotNullOrBlank = context -> StringUtils
						.isNotBlank(context.getSource());

				Converter<String, UUID> stringToUuidConverter = context -> UUID
						.fromString(context.getSource());

				Converter<UserDTO, String> userDtoPasswordConverter = context -> {
					UserDTO user = context.getSource();

					if (StringUtils.isBlank(user.getPassword())) {
						UUID uuid = UUID.fromString(user.getId());
						return userBO.findMandatoryById(uuid)
								.getPassword();
					}
					
					return user.getPassword();
				};

				when(stringIsNotNullOrBlank).using(stringToUuidConverter)
						.map(source.getId(), destination.getId());
				using(userDtoPasswordConverter).map(source, destination.getPassword());
			}
		};

		modelMapper.addMappings(userDtoToUserPoPropertyMap);
	}
}
