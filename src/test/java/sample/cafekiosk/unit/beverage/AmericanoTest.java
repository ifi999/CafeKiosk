package sample.cafekiosk.unit.beverage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    public void getName() {
        //given
        Americano americano = new Americano();

        //when
        String name = americano.getName();

        //then
//        assertEquals(name, "아메리카노");
        assertThat(name).isEqualTo("아메리카노");
    }

    @Test
    public void getPrice() {
        //given
        Americano americano = new Americano();

        //when
        int price = americano.getPrice();

        //then
        assertThat(price).isEqualTo(4500);
    }

}