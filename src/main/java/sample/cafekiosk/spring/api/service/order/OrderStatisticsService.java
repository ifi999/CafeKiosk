package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.mail.MailService;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * transactional을 걸지 않은 이유
 * - DB 커넥션을 계속 붙잡고 있는데, 메일 전송과 같이 긴 작업에는 걸지 않는 것이 좋다.
 *   실제로는 트랜잭션에는 참여하지 않아도 되기 때문
 */

@Service
@RequiredArgsConstructor
public class OrderStatisticsService {

    private final OrderRepository orderRepository;
    private final MailService mailService;

    public boolean sendOrderStatisticsMail(LocalDate orderDate, String email) {
        // 해당 일자에 결제 완료된 주문들을 가져와서
        List<Order> orders = orderRepository.findOrdersBy(
                orderDate.atStartOfDay()
                , orderDate.plusDays(1).atStartOfDay()
                , OrderStatus.PAYMENT_COMPLETED
        );

        // 총 매출합계를 계산하여
        int totalAmount = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();

        // 메일로 전송
        boolean result = mailService.sendMail("no-reply@cafekiosk.com"
                , email
                , String.format("[매출통계] %s", orderDate)
                , String.format("총 매출 합계는 %s원입니다.", totalAmount));

        if (!result) {
            throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
        }

        return true;
    }

}
