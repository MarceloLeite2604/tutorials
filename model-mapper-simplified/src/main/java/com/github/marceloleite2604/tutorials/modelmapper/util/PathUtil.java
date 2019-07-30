package com.github.marceloleite2604.tutorials.modelmapper.util;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PathUtil {
	
	public static final String SEPARATOR = "/";

	private static final String REGEX_SLASH_SPACE_CHARACTERS = "[\\\\\\/\\s]";

	private static final String REGEX_UNNACCEPTABLE_CHARACTERS = "[^A-z0-9\\-]";

	private static final String REGEX_MULTIPLE_SEPARATORS = "(?<!:)[" + SEPARATOR + "]{2,}";

	public String formatAsPathFriendly(String text) {
		String result = text.toLowerCase();
		result = result.replaceAll(REGEX_SLASH_SPACE_CHARACTERS, "-");
		result = StringUtils.stripAccents(result);
		return removeSpecialCharacters(result);
	}

	private String removeSpecialCharacters(String text) {
		return text.replaceAll(REGEX_UNNACCEPTABLE_CHARACTERS, "");
	}

	public String appendSeparatorIfNecessary(String text) {

		if (Objects.isNull(text)) {
			return null;
		}

		if (!text.endsWith(SEPARATOR)) {
			return text.concat(SEPARATOR);
		}
		return text;
	}

	public String removeMultipleSeparators(String text) {
		return text.replaceAll(REGEX_MULTIPLE_SEPARATORS, SEPARATOR);
	}

}
