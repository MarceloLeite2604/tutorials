package org.marceloleite;

import java.io.File;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

public class Application {

    public static void main(String[] args) {
        ProjectConnection connection = GradleConnector.newConnector().forProjectDirectory(new File("/home/marcelo/tutorial-gradle/thirdGradleProject")).connect();
        try {
            BuildLauncher build = connection.newBuild();
            build.forTasks("build");
            build.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
