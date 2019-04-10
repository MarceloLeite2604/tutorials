package com.github.marceloleite2604.graphs.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

	public static final String DEFAULT_DIRECTORY_PATH = "src/main/resources/";

	public static final Pattern INPUT_FILE_NAME_PATTERN = Pattern.compile("input([0-9]+)\\.txt");

	public static int getFileNumber(String fileName) {
		Matcher matcher = FileUtil.INPUT_FILE_NAME_PATTERN.matcher(fileName);
		if (!matcher.matches()) {
			throw new RuntimeException("Could not create file name. No file name matches pattern \""
					+ FileUtil.INPUT_FILE_NAME_PATTERN.pattern() + "\".");
		}

		int number = Integer.parseInt(matcher.group(1));
		return number;
	}

	public static int getFileNumber(File file) {
		return getFileNumber(file.getName());
	}

	public static void throwErrorIfFileDoesNotExist(String filePath) {
		if (!new File(filePath).exists()) {
			throw new IllegalArgumentException("File \"" + filePath + "\" does not exist.");
		}
	}

	public static void throwErrorIfFileExists(String filePath) {
		if (new File(filePath).exists()) {
			throw new IllegalArgumentException("File \"" + filePath + "\" already exists.");
		}
	}
}
