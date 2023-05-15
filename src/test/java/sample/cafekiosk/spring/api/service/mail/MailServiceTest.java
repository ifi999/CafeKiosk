package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
//    @Spy // 실제 객체를 기반으로 만듦. 일부만 Stubbing 하도록 만들 수 있다.
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일을 전송한다.")
    @Test
    public void sendMail() {
        // given
        // 아래 2줄은 위의 @Mock으로 대체 가능하다.
//        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        // 이것도 @InjectMocks로 대체된다.
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        String fromEmail = "from email";
        String toEmail = "to email";
        String subject = "subject1";
        String content = "content1";

        // @Spy는 실제 객체를 기반으로 만들기 때문에 when을 사용할 수 없음. 대신 do~를 사용함.
//        Mockito.when(mailSendClient.sendEmail(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class)))
//                .thenReturn(true);

        // log 찍는 기능은 실제 객체에 있는 것으로 나오고, 아래 정의한 것만 Stubbing함.
//        Mockito.doReturn(true)
//                .when(mailSendClient)
//                .sendEmail(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class));

        // 여긴 given 위치인데 왜 Mockito.when을 사용해야함??
        // -> 그래서 BDDMockito.given() 이 생긴듯. 내부 보면 Mockito를 감싼 것
        BDDMockito.given(mailSendClient.sendEmail(ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class), ArgumentMatchers.any(String.class)))
                .willReturn(true);

        // when
        boolean result = mailService.sendMail(fromEmail, toEmail, subject, content);

        // then
        Mockito.verify(mailSendHistoryRepository, Mockito.times(1)).save(ArgumentMatchers.any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

}