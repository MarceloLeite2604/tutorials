package io.github.marceloleite2604.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PathUtil {

    private static final String PATH_SEPARATOR = "/";

    private static final String MULTIPLE_PATH_SEPARATOR_REGEX = "\\/{2,}";

    public String appendSeparatorIfNecessary(String text) {
        return !text.endsWith(File.separator) ? text.concat(PATH_SEPARATOR) : text;
    }

    public String removeMultipleSeparators(String text) {
        return text.replace(MULTIPLE_PATH_SEPARATOR_REGEX, PATH_SEPARATOR);
    }
}
