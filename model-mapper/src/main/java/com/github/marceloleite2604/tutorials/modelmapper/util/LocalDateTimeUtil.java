package com.github.marceloleite2604.tutorials.modelmapper.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.configuration.MessageSourceConfiguration;

@Component
public class LocalDateTimeUtil {

	private static final DateTimeFormatter DATE_TIME_FORMATTER_HUMAN_READABLE_DATE = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");

	private static final DateTimeFormatter DATE_TIME_FORMATTER_HUMAN_READABLE_DATE_TIME = DateTimeFormatter
			.ofPattern("dd/MM/yyyy kk:mm:ss");
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER_POST_PUBLICATION_DATE = DateTimeFormatter
			.ofPattern("dd MMM", MessageSourceConfiguration.LOCALE_BRAZILIAN_PORTUGUESE);

	public String formatToHumanReadableDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return localDateTime.format(DATE_TIME_FORMATTER_HUMAN_READABLE_DATE_TIME);
	}

	public String formatToHumanReadableDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return localDateTime.format(DATE_TIME_FORMATTER_HUMAN_READABLE_DATE);
	}

	public LocalDateTime parseFromHumanReadable(String text) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		return LocalDateTime.parse(text, DATE_TIME_FORMATTER_HUMAN_READABLE_DATE_TIME);
	}
	
	public String formatAsPostPublicationDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		String dateText = localDateTime.format(DATE_TIME_FORMATTER_POST_PUBLICATION_DATE);
		String[] splittedDateText = dateText.split("\\s");
		splittedDateText[1] = StringUtils.capitalize(splittedDateText[1]);
		return String.join(" ", splittedDateText);
	}

}