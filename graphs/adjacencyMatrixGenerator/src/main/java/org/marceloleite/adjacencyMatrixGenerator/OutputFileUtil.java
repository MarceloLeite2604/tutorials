package org.marceloleite.adjacencyMatrixGenerator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputFileUtil {

	public static final Pattern FILE_NAME_PATTERN = Pattern.compile("input([0-9]+)\\.txt");
	
	public static int getFileNumber(String fileName) {
		Matcher matcher = OutputFileUtil.FILE_NAME_PATTERN.matcher(fileName);
		if (!matcher.matches()) {
			throw new RuntimeException("Could not create file name. No file name matches pattern \""
					+ OutputFileUtil.FILE_NAME_PATTERN.pattern() + "\".");
		}
		return Integer.parseInt(matcher.group(1));
	}
	
	public static int getFileNumber(File file) {
		return getFileNumber(file.getName());
	}
}
