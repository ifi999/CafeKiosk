package sample.cafekiosk.spring.client.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.IntegrationTestSupport;

import static org.assertj.core.api.Assertions.assertThat;

class MailSendClientTest extends IntegrationTestSupport {

    @DisplayName("메일을 전송한다.")
    @Test
    public void sendEmail() {
        // given
        String fromEmail = "from email";
        String toEmail = "to email";
        String subject = "subject1";
        String content = "content1";

        // then
        boolean result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content);

        // then
        assertThat(result).isFalse();
    }

}