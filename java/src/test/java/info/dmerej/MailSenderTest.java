package info.dmerej;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import info.dmerej.mailprovider.SendMailRequest;
import info.dmerej.mailprovider.SendMailResponse;

@ExtendWith(MockitoExtension.class)
public class MailSenderTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private MailSender mailSender;

    User user = new User("A", "A@email.com");


    @Test
    void should_make_a_valid_http_request() {
        SendMailRequest expectedRequest = new SendMailRequest(user.email(),"New notification", "Message");
        mailSender.sendV1(user, "Message");
        verify(httpClient).post("https://api.mailprovider.com/v3/", expectedRequest);
    }

    @Test
    void should_retry_when_getting_a_503_error() {
        SendMailRequest request = new SendMailRequest(user.email(), "New notification", "Message");
        SendMailResponse response = new SendMailResponse(503, "Error");
        when(httpClient.post("https://api.mailprovider.com/v3/", request)).thenReturn(response);

        mailSender.sendV2(user, "Message");

        verify(httpClient, times(2)).post("https://api.mailprovider.com/v3/", request);
    }
}
