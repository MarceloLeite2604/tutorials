package com.github.marceloleite2604.tutorials;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;

class FileReadingTest {

    private static final String INVALID_FILE_PATH = "src/main/resouces/inexistent-file.txt";

    //private static final Path INVALID_FILE_PATH = Path.of("src/main/resouces/inexistent-file.txt");
    private static final Path VALID_FILE_PATH = Path.of("src/main/resouces/test-file.txt");

    @SuppressWarnings("java:S2699")
    @Test
    void readValidFile() {
        File file = new File(INVALID_FILE_PATH);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Exception thrown: " + fileNotFoundException.getMessage());
        }
    }

    @SuppressWarnings("java:S2699")
    @Test
    void readInvalidFile() {

        File file = new File(INVALID_FILE_PATH);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Exception thrown: " + fileNotFoundException.getMessage());
        }
    }


    void createException() {


        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("This argument is illegal.");





        throw new IllegalArgumentException("This argument is illegal.");


    }
}
