package com.github.marceloleite2604.tutorials.myapp.service;

public class EmailService {

    public void sendEmailTo(String from, String to, String subject, String body) {
        System.out.println("FROM: \"" + from + "\".");
        System.out.println("TO: \"" + to + "\".");
        System.out.println("SUBJECT: " + subject);
        System.out.println("BODY:\n" + body);
        System.out.println("---\n");
    }
}
