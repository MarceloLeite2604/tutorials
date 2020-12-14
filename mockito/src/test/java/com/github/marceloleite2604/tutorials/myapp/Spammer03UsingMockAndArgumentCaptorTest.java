package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.EmailService;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Spammer03UsingMockAndArgumentCaptorTest {

    @Mock
    private UserFileService userFileService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private Spammer spammer;

    @Captor
    private ArgumentCaptor<String> emailCaptor;

    @Test
    void runShouldSendEmailToSpecifiedEmails() throws Exception {

        List<String> emails = Collections.unmodifiableList(List.of("2d@gorillaz.com", "murdoc@gorillaz.com", "noodles@gorillaz.com", "russel@gorillaz.com"));

        // Arrange
        User twoD = User.Builder.anUser()
            .id(0L)
            .username("2d")
            .email(emails.get(0))
            .dataOfBirth(LocalDate.of(1953, 7, 19))
            .build();

        User murdoc = User.Builder.anUser()
            .id(1L)
            .username("murdoc")
            .email(emails.get(1))
            .dataOfBirth(LocalDate.of(1953, 8, 27))
            .build();

        User noodles = User.Builder.anUser()
            .id(2L)
            .username("noodles")
            .email(emails.get(2))
            .dataOfBirth(LocalDate.of(1952, 9, 21))
            .build();

        User russel = User.Builder.anUser()
            .id(3L)
            .username("russel")
            .email(emails.get(3))
            .dataOfBirth(LocalDate.of(1952, 7, 23))
            .build();

        Mockito.when(userFileService.read(Mockito.anyLong()))
            .thenReturn(twoD)
            .thenReturn(murdoc)
            .thenReturn(noodles)
            .thenReturn(russel);

        // Act

        spammer.run();

        // Assert

        /* Check if EmailService.sendEmailTo method was executed the specified amount of times. */
        /* And captures the second argument informed on every execution. */
        Mockito.verify(emailService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .sendEmailTo(Mockito.anyString(), emailCaptor.capture(), Mockito.anyString(), Mockito.anyString());

        List<String> capturedEmailAddresses = emailCaptor.getAllValues();
        assertThat(capturedEmailAddresses).containsAll(emails);

    }

    @Test
    void runShouldRetrieveDataForUsersOneThroughFourFromFiles() throws Exception {

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        // Arrange
        User twoD = User.Builder.anUser()
            .id(0L)
            .username("2d")
            .email("2d@gorillaz.com")
            .dataOfBirth(LocalDate.of(1953, 7, 19))
            .build();

        User murdoc = User.Builder.anUser()
            .id(1L)
            .username("murdoc")
            .email("murdoc@gorillaz.com")
            .dataOfBirth(LocalDate.of(1953, 8, 27))
            .build();

        User noodles = User.Builder.anUser()
            .id(2L)
            .username("noodles")
            .email("noodles@gorillaz.com")
            .dataOfBirth(LocalDate.of(1952, 9, 21))
            .build();

        User russel = User.Builder.anUser()
            .id(3L)
            .username("russel")
            .email("russel@gorillaz.com")
            .dataOfBirth(LocalDate.of(1952, 7, 23))
            .build();

        Mockito.when(userFileService.read(Mockito.anyLong()))
            .thenReturn(twoD)
            .thenReturn(murdoc)
            .thenReturn(noodles)
            .thenReturn(russel);

        // Act
        spammer.run();

        // Assert

        /* Check if UserFileService.read method was executed the specified amount of times. */
        /* And captures the argument informed on every execution. */
        Mockito.verify(userFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(idCaptor.capture());

        /* Checks if all ids from 1 through 4 were used to retrieve data from files. */
        List<Long> ids = idCaptor.getAllValues();
        assertThat(ids).contains(0L, 1L, 2L, 3L);
    }
}
