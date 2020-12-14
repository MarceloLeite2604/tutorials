package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.EmailService;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class Spammer04UsingSpyTest {

    @InjectMocks
    private Spammer spammer;

    @Mock
    private UserFileService userFileService;

    /*
     * Spy annotation works a little bit different than Mock. It wraps a real instance of EmailService into another
     * object exactly equals of EmailService, with same methods and returns. Once a method is invoked, it passes all
     * arguments to the real object and returns the result received from it, but it allow us to do other things like count
     * the amount of times a method was invoked and which arguments were informed.
     */
    @Spy
    private EmailService emailService;

    @Test
    void runShouldSendEmailToAllUsers() throws Exception {
        // Arrange
        User geddyLee = User.Builder.anUser()
            .id(0L)
            .username("geddy.lee")
            .email("geddy.lee@rush.com")
            .dataOfBirth(LocalDate.of(1953, 7, 19))
            .build();

        User alexLifeson = User.Builder.anUser()
            .id(1L)
            .username("alex.lifeson")
            .email("alex.lifeson@rush.com")
            .dataOfBirth(LocalDate.of(1953, 8, 27))
            .build();

        User neilPeart = User.Builder.anUser()
            .id(2L)
            .username("neil.peart")
            .email("neil.peart@rush.com")
            .dataOfBirth(LocalDate.of(1952, 9, 21))
            .build();

        User johnRutsey = User.Builder.anUser()
            .id(3L)
            .username("john.rutsey")
            .email("john.rutsey@rush.com")
            .dataOfBirth(LocalDate.of(1952, 7, 23))
            .build();

        Mockito.when(userFileService.read(Mockito.anyLong()))
            .thenReturn(geddyLee)
            .thenReturn(alexLifeson)
            .thenReturn(neilPeart)
            .thenReturn(johnRutsey);

        // Act
        /* This time, when EmailService.sendEmailTo is invoked, it will execute the real method (content will be
         * printed on console output).
         */
        spammer.run();

        // Assert
        Mockito.verify(userFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(Mockito.anyLong());

        /* This test is the same as is we were using a mocked up object, but this time we invoked the real method
        instead of a mocked one. */
        Mockito.verify(emailService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .sendEmailTo(Mockito.anyString(), ArgumentCaptor.forClass(String.class).capture(), Mockito.anyString(), Mockito.anyString());

    }
}
