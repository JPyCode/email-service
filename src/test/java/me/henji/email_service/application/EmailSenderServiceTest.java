package me.henji.email_service.application;

import me.henji.email_service.adapters.EmailSenderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EmailSenderServiceTest {

    @Mock
    private EmailSenderGateway emailSenderGateway;

    @InjectMocks
    private EmailSenderService emailSenderService;

    @Test
    public void testSendEmail_ShouldCallSendEmail() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // Act
        emailSenderService.sendEmail(to, subject, body);

        // Assert
        verify(emailSenderGateway, times(1)).sendEmail(to, subject, body);
    }
}
