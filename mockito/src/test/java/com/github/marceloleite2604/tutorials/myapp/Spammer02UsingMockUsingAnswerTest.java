package com.github.marceloleite2604.tutorials.myapp;

import com.github.marceloleite2604.tutorials.myapp.model.User;
import com.github.marceloleite2604.tutorials.myapp.service.EmailService;
import com.github.marceloleite2604.tutorials.myapp.service.UserFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class Spammer02UsingMockUsingAnswerTest {

    @Mock
    private UserFileService userFileService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private Spammer spammer;

    @Test
    void runShouldSendEmailToAllUsers() throws Exception {
        // Arrange

        /*
         * For more complex scenarios (like when the result must be customized depending of an input parameter)
         * it is possible to use "thenAnswer" to elaborate the returned object.
         */
        Mockito.when(userFileService.read(Mockito.anyLong()))
            /* "invocation" parameter has all parameters informed for the invocation (plus other things). */
            .thenAnswer(invocation -> {
                Long id = invocation.getArgument(0);
                String username = "user" + id;
                String email = username + "@mockedobject.com";
                LocalDate dateOfBirth = LocalDate.now();

                return User.Builder.anUser()
                    .id(id)
                    .username(username)
                    .email(email)
                    .dataOfBirth(dateOfBirth)
                    .build();
            });

        // Act
        spammer.run();

        // Assert
        Mockito.verify(userFileService, Mockito.times(Spammer.TOTAL_USERS.intValue()))
            .read(Mockito.anyLong());
    }
}
