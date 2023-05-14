package sample.cafekiosk.spring.client.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailSendClientTest {

    @Autowired
    private MailSendClient mailSendClient;

    @DisplayName("메일을 전송한다.")
    @Test
    public void sendEmail() {
        // given
        String fromEmail = "from email";
        String toEmail = "to email";
        String subject = "subject1";
        String content = "content1";

        // when
        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);

        // then
        assertThat(result).isTrue();
    }

}