package sample.cafekiosk.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CafeKioskTest {

    @DisplayName("키오스크에 음료 1개 추가하면 주문 목록에 담긴다.")
    @Test
    public void add() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();

        //when
        cafeKiosk.add(new Americano());

        //then
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("키오스크에 음료 여러개 추가하면 주문 목록에 담긴다.")
    @Test
    public void addSeveralBeverages() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();

        //when
        Americano americano = new Americano();
        cafeKiosk.add(americano,2);

        //then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(2);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("키오스크에 음료 0개를 추가할 수 없다.")
    @Test
    public void addZeroBeverages() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        //then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문할 수 있다.");
    }

    @DisplayName("키오스크에 담긴 주문 목록 중 음료 1개를 제거한다.")
    @Test
    public void remove() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        //when
        cafeKiosk.remove(americano);

        //then
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("키오스크에 담긴 주문 목록을 모두 비운다.")
    @Test
    public void clear() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        //when
        cafeKiosk.clear();

        //then
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("키오스크에 담긴 주문 목록을 주문 생성한다.")
    @Test
    public void createOrder() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        //when
        Order order = cafeKiosk.createOrder();

        //then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("키오스크에 담긴 주문 목록을 영업 시간에 주문 생성한다.")
    @Test
    public void createOrderWithCurrentTime() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        //when
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 5, 7, 10, 0));

        //then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("키오스크에 담긴 주문 목록을 영업 시간 이외에 주문 생성할 수 없다.")
    @Test
    public void createOrderOutsideOpenTime() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        //then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2023, 5, 7, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아니다.");
    }

    @DisplayName("키오스크에 담긴 주문 목록의 총 합계 가격을 출력한다.")
    @Test
    public void calculateTotalPrice() {
        //given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        //when
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        int totalPrice = cafeKiosk.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(9500);
    }

}