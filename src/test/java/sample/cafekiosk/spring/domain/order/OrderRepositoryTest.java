package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.IntegrationTestSupport;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("원하는 기간 내의 원하는 주문 상태인 주문들을 조회한다.")
    @Test
    public void findOrdersBy() {
        // given
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().plusDays(1);
        OrderStatus orderStatus = OrderStatus.COMPLETED;

        // when
        List<Order> orders = orderRepository.findOrdersBy(startDateTime, endDateTime, orderStatus);

        // then
        assertThat(orders).hasSize(0);
    }

}