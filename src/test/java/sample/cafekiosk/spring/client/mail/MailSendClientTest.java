package sample.cafekiosk.spring.client.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        // then
        assertThatThrownBy(() -> mailSendClient.sendEmail(fromEmail, toEmail, subject, content))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("메일 전송");
    }

}