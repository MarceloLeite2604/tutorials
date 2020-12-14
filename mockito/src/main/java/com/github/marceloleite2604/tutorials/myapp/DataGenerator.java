package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.exception.MyAppRuntimeException;
import com.github.marceloleite2604.tutorials.myapp.exception.UserFileException;
import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * This class is used only to generate content and should not be considered.
 */
public class DataGenerator {

    private static final UserFileService userFileService = new UserFileService();

    public static void main(String[] args) {

        createUsersDirectory();

        User johnLennon = User.Builder.anUser()
            .id(0L)
            .username("john.lennon")
            .email("john.lennon@beatles.com")
            .dataOfBirth(LocalDate.of(1940, 10, 9))
            .build();

        User paulMccartney = User.Builder.anUser()
            .id(1L)
            .username("paul.mccartney")
            .email("paul.mccartney@beatles.com")
            .dataOfBirth(LocalDate.of(1942, 6, 16))
            .build();

        User georgeHarrisson = User.Builder.anUser()
            .id(2L)
            .username("george.harrison")
            .email("george.harrison@beatles.com")
            .dataOfBirth(LocalDate.of(1943, 2, 25))
            .build();

        User ringoStar = User.Builder.anUser()
            .id(3L)
            .username("ringo.star")
            .email("ringo.star@beatles.com")
            .dataOfBirth(LocalDate.of(1940, 7, 7))
            .build();

        writeUser(johnLennon);
        writeUser(paulMccartney);
        writeUser(georgeHarrisson);
        writeUser(ringoStar);
    }

    private static void createUsersDirectory() {
        Path usersDirectoryPath = Path.of(UserFileService.USERS_DIRECTORY_PATH);

        if (Files.exists(usersDirectoryPath)) {
            if (!Files.isDirectory(usersDirectoryPath)) {
                String message = String.format("Path \"%s\" refers to a file, not a directory.", usersDirectoryPath.toString());
                throw new MyAppRuntimeException(message);
            }
            return;
        }

        try {
            Files.createDirectory(usersDirectoryPath);
        } catch (IOException ioException) {
            String message = String.format("Could not create directory \"%s\".", UserFileService.USERS_DIRECTORY_PATH);
            throw new MyAppRuntimeException(message);
        }
    }

    private static void writeUser(User user) {
        try {
            userFileService.write(user);
        } catch (UserFileException userFileException) {
            String message = String.format("Could not write user \"%s\".", user.getUsername());
            throw new MyAppRuntimeException(message, userFileException);
        }
    }
}
