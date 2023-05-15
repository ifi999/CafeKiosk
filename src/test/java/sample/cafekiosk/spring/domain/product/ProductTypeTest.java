package sample.cafekiosk.spring.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductTypeTest {

//    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.")
//    @Test
//    public void containsStockType() {
//        // given
//        ProductType givenType = ProductType.HANDMADE;
//
//        // when
//        boolean result = ProductType.containsStockType(givenType);
//
//        // then
//        assertThat(result).isFalse();
//    }
//
//    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.")
//    @Test
//    public void containsStockType2() {
//        // given
//        ProductType givenType = ProductType.BAKERY;
//
//        // when
//        boolean result = ProductType.containsStockType(givenType);
//
//        // then
//        assertThat(result).isTrue();
//    }

    @CsvSource({"HANDMADE, false", "BOTTLE, true", "BAKERY, true"})
    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.")
    @ParameterizedTest
    public void containsStockType(ProductType productType, boolean expected) {
        // when
        boolean result = ProductType.containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

}