package com.github.marceloleite2604.tutorials;

import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingEnhancer;

public class AppLoggingEnhancer implements LoggingEnhancer {

  @Override
  public void enhanceLogEntry(LogEntry.Builder logEntry) {
    logEntry.addLabel("git-commit", "abcdef012345");
    logEntry.addLabel("project-version", "1.0-SNAPSHOT");
  }
}
