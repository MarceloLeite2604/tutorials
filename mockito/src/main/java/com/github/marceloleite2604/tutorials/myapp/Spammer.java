package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.exception.MyAppRuntimeException;
import com.github.marceloleite2604.tutorials.myapp.exception.UserFileException;
import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.EmailService;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Spammer {

    private final UserFileService userFileService;

    private final EmailService emailService;

    static final Long TOTAL_USERS = 4L;

    public Spammer(UserFileService userFileService, EmailService emailService) {
        this.userFileService = userFileService;
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        UserFileService userFileService = new UserFileService();
        EmailService emailService = new EmailService();
        new Spammer(userFileService, emailService).run();
    }

    public void run() {
        List<User> users = retrieveAllUsers();
        sendEmails(users);
    }

    private List<User> retrieveAllUsers() {
        return LongStream.range(0L, TOTAL_USERS) // [0, 1, 2, 3]
            .mapToObj(this::retrieveUser)
            .collect(Collectors.toList());
    }

    private User retrieveUser(long id) {
        try {
            return userFileService.read(id);
        } catch (UserFileException userFileException) {
            System.err.println("DEBUG: The following exception was caught: " + userFileException.getMessage());
            String message = String.format("Error while retrieving user with id %d.", id);
            throw new MyAppRuntimeException(message, userFileException);
        }
    }

    private void sendEmails(List<User> users) {
        users.stream()
            .map(User::getEmail)
            .forEach(this::sendEmail);
    }

    private void sendEmail(String address) {
        emailService.sendEmailTo("marketing@piedpipermusicstore.com", address, "Don't miss this opportunity ⭐️", "Lightning fast promotion on Pied Piper music store! ⚡️\nHurry up to our nearest store and check the discounts on our best instruments.");
    }


}
