package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.exception.MyAppRuntimeException;
import com.github.marceloleite2604.tutorials.myapp.exception.UserFileException;
import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.EmailService;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class Spammer01UsingMockTest {

    @Test
    void runShouldSendEmailToAllUsers() throws Exception {

        // Arrange

        /* Creates some fictional User data */
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

        /* Define mocked up objects. */
        UserFileService mockedUserFileService = Mockito.mock(UserFileService.class);
        EmailService mockedEmailService = Mockito.mock(EmailService.class);

        /* Creates an actual Spammer instance. */
        Spammer spammer = new Spammer(mockedUserFileService, mockedEmailService);

        /*
         * Mocks up the expected behaviour when "userFileService.read" method is executed.
         * Each "thenReturn" contains the object returned according to each execution. So, first return will be
         * "geddyLee" object, second one will be "alexLifeson", third one is "neilPeart" and last one will be
         * "johnRutsey".
         */
        Mockito.when(mockedUserFileService.read(Mockito.anyLong()))
            .thenReturn(geddyLee)
            .thenReturn(alexLifeson)
            .thenReturn(neilPeart)
            .thenReturn(johnRutsey);

        // Act

        spammer.run();

        // Assert

        /* Checks if UserFileService.read method was executed the specified amount of times (four, in this case). */
        Mockito.verify(mockedUserFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(Mockito.anyLong());

        /* Checks if EmailService.sendEmailTo method was executed the specified amount of times (also four). */
        Mockito.verify(mockedEmailService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .sendEmailTo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void runShouldSendEmailToAllUsersUsingMockitoEq() throws Exception {

        // Arrange

        /* Creates some fictional User data */
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

        /* Define mocked up objects. */
        UserFileService mockedUserFileService = Mockito.mock(UserFileService.class);
        EmailService mockedEmailService = Mockito.mock(EmailService.class);

        /* Creates an actual Spammer instance. */
        Spammer spammer = new Spammer(mockedUserFileService, mockedEmailService);

        /*
         * "Mockito.when" also allow us to the returned values depending on the values informed as paramters. For these
         * cases we use Mockito.eq to compare the values informed.
         */

        Mockito.when(mockedUserFileService.read(Mockito.eq(3L)))
            .thenReturn(johnRutsey);

        Mockito.when(mockedUserFileService.read(Mockito.eq(2L)))
            .thenReturn(neilPeart);

        Mockito.when(mockedUserFileService.read(Mockito.eq(1L)))
            .thenReturn(alexLifeson);

        Mockito.when(mockedUserFileService.read(Mockito.eq(0L)))
            .thenReturn(geddyLee);

        // Act

        spammer.run();

        // Assert

        Mockito.verify(mockedUserFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(Mockito.anyLong());

        Mockito.verify(mockedEmailService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .sendEmailTo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void runShouldSendEmailToAllUsersUsingMockedUser() throws Exception {

        // Arrange

        /* Define mocked up objects. */
        UserFileService mockedUserFileService = Mockito.mock(UserFileService.class);
        EmailService mockedEmailService = Mockito.mock(EmailService.class);

        /* Creates an actual Spammer instance. */
        Spammer spammer = new Spammer(mockedUserFileService, mockedEmailService);

        /* Uses "Mockito.mock" to create a mocked up instance of "User". */
        User mockedUser = Mockito.mock(User.class);

        /* Define the mocked up User behaviour. */
        Mockito.when(mockedUser.getEmail())
            .thenReturn("anthony.kiedis@rhcp.com")
            .thenReturn("chad.smith@rhcp.com")
            .thenReturn("flea@rhcp.com")
            .thenReturn("dave.navarro@rhcp.com");

        /*
         * If the mocked up object has a behaviour defined it will repeat its last response as many times as it was
         * executed. The below behaviour definition means that everytime UserFileService.read is executed it will
         * return "mockedUser" object.
         */
        Mockito.when(mockedUserFileService.read(Mockito.anyLong()))
            .thenReturn(mockedUser);

        // Act

        spammer.run();

        // Assert

        Mockito.verify(mockedUserFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(Mockito.anyLong());

        Mockito.verify(mockedEmailService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .sendEmailTo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void runShouldThrowMyAppRuntimeExceptionWhenCannotReadUserFile() throws Exception {
        // Arrange

        /* Define mocked up objects. */
        UserFileService mockedUserFileService = Mockito.mock(UserFileService.class);
        EmailService mockedEmailService = Mockito.mock(EmailService.class);

        /* Creates an actual Spammer instance. */
        Spammer spammer = new Spammer(mockedUserFileService, mockedEmailService);

        /* This time, when UserFileService.read is executed. It will throw a UserFileException class. */
        Mockito.when(mockedUserFileService.read(Mockito.anyLong()))
            .thenThrow(UserFileException.class);

        // Act and Assert

        /*
         * "Assertions.assertThrows" is a method used to check if a specified exception was thrown during an execution.
         * Its first parameter is the expected exception, and the second one is a method which will throw the exception
         * when invoked.
         */
        assertThrows(MyAppRuntimeException.class, () -> spammer.run());
    }

    @Test
    void runShouldThrowMyAppRuntimeExceptionWhenCannotReadUserFileUsingCustomException() throws Exception {
        // Arrange

        /* Define mocked up objects. */
        UserFileService mockedUserFileService = Mockito.mock(UserFileService.class);
        EmailService mockedEmailService = Mockito.mock(EmailService.class);

        /* Creates an actual Spammer instance. */
        Spammer spammer = new Spammer(mockedUserFileService, mockedEmailService);

        /* Same as the previous test, but this time it will throw an already defined exception with a custom message. */
        Mockito.when(mockedUserFileService.read(Mockito.anyLong()))
            .thenThrow(new UserFileException("Mocked exception."));

        // Act and Assert

        /*
         * Same as the previous test, but this time we wrote a little bit different. Since "run" method does not have
         * any argument and does not return any value, we can pass its signature directly as "assertThrows" second
         * parameter.
         */
        assertThrows(MyAppRuntimeException.class, spammer::run);
    }
}
