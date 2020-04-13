package io.github.marceloleite2604.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(PropertiesPath.MESSAGES)
public class MessagesProperties {

    private String fileBaseDirectoryPath;

    private List<String> additionalMessageFilePaths;

    public String getFileBaseDirectoryPath() {
        return fileBaseDirectoryPath;
    }

    public void setFileBaseDirectoryPath(String fileBaseDirectoryPath) {
        this.fileBaseDirectoryPath = fileBaseDirectoryPath;
    }

    public List<String> getAdditionalMessageFilePaths() {
        return additionalMessageFilePaths;
    }

    public void setAdditionalMessageFilePaths(List<String> additionalMessageFilePaths) {
        this.additionalMessageFilePaths = additionalMessageFilePaths;
    }
}
