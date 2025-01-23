package me.henji.email_service.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import me.henji.email_service.core.exceptions.EmailServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SesEmailSenderTest {

    @Mock
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @InjectMocks
    private SesEmailSender sesEmailSender;

    @Test
    public void testSendEmail_ShouldCallAwsSesToSendEmail() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        SendEmailRequest request = new SendEmailRequest()
                .withSource("darkeverwing@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message().withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body))));

        // Mocking the sendEmail behavior
        SendEmailResult sendEmailResult = mock(SendEmailResult.class);
        when(amazonSimpleEmailService.sendEmail(request)).thenReturn(sendEmailResult);

        // Act
        sesEmailSender.sendEmail(to, subject, body);

        // Assert
        verify(amazonSimpleEmailService, times(1)).sendEmail(request);
    }

    @Test
    public void testSendEmail_ShouldThrowException_WhenAwsServiceFails() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";
        SendEmailRequest request = new SendEmailRequest()
                .withSource("darkeverwing@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message().withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body))));

        // Mocking an exception
        when(amazonSimpleEmailService.sendEmail(request)).thenThrow(new AmazonServiceException("AWS SES error"));

        // Act & Assert
        assertThrows(EmailServiceException.class, () -> sesEmailSender.sendEmail(to, subject, body));
    }
}
