package com.github.marceloleite2604.tutorials.myapp.service;

import com.github.marceloleite2604.tutorials.myapp.exception.UserFileException;
import com.github.marceloleite2604.tutorials.myapp.model.User;

import java.io.*;

public class UserFileService {

    public static final String USERS_DIRECTORY_PATH = "src/main/resources/users".replace("/", File.separator);

    private static final String FILE_SUFFIX = ".ser";

    public User read(long id) throws UserFileException {
        String filePath = elaborateFilePath(id);

        try (final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object object = objectInputStream.readObject();

            if (!(object instanceof User)) {
                String message = String.format("Object read from file \"%s\" is of type \"%s\", not  \"%s\" as expected.", filePath, object.getClass()
                    .toString(), User.class.toString());
                throw new UserFileException(message);
            }
            return (User) object;
        } catch (IOException | ClassNotFoundException exception) {
            String message = String.format("Exception thrown while reading user \"%d\" from file \"%s\".", id, filePath);
            throw new UserFileException(message, exception);
        }
    }

    public void write(User user) throws UserFileException {
        String filePath = elaborateFilePath(user.getId());
        try (final ObjectOutputStream objetOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objetOutputStream.writeObject(user);
        } catch (IOException exception) {
            String message = String.format("Exception thrown while writing user \"%d\" on file \"%s\".", user.getId(), filePath);
            throw new UserFileException(message, exception);
        }
    }

    private String elaborateFilePath(long id) {
        return USERS_DIRECTORY_PATH + File.separator + id + FILE_SUFFIX;
    }
}
