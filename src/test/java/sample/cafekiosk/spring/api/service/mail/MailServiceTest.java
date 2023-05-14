package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @DisplayName("메일을 전송한다.")
    @Test
    public void sendMail() {
        // given
        String fromEmail = "from email";
        String toEmail = "to email";
        String subject = "subject1";
        String content = "content1";

        // when
        boolean result = mailService.sendMail(fromEmail, toEmail, subject, content);

        // then
        assertThat(result).isTrue();
    }

}