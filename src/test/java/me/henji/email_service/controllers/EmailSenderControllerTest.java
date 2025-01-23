package me.henji.email_service.controllers;

import me.henji.email_service.application.EmailSenderService;
import me.henji.email_service.core.EmailRequest;
import me.henji.email_service.core.exceptions.EmailServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class EmailSenderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmailSenderService emailSenderService;

    @InjectMocks
    private EmailSenderController emailSenderController;

    @Test
    public void testSendEmail_ShouldReturnOk_WhenEmailSent() throws Exception {
        // Arrange
        EmailRequest emailRequest = new EmailRequest("test@example.com", "Test Subject", "Test Body");

        // Mocking the service
        doNothing().when(emailSenderService).sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());

        mockMvc = MockMvcBuilders.standaloneSetup(emailSenderController).build();

        // Act & Assert
        mockMvc.perform(post("/api/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"to\": \"test@example.com\", \"subject\": \"Test Subject\", \"body\": \"Test Body\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent successfully!"));
    }

    @Test
    public void testSendEmail_ShouldReturnInternalServerError_WhenServiceFails() throws Exception {
        // Arrange
        EmailRequest emailRequest = new EmailRequest("test@example.com", "Test Subject", "Test Body");

        // Mocking the service to throw an exception
        doThrow(new EmailServiceException("Error")).when(emailSenderService).sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());

        mockMvc = MockMvcBuilders.standaloneSetup(emailSenderController).build();

        // Act & Assert
        mockMvc.perform(post("/api/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"to\": \"test@example.com\", \"subject\": \"Test Subject\", \"body\": \"Test Body\" }"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error while sending email"));
    }
}
