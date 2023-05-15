package sample.cafekiosk.spring.api.service.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.BAKERY;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@Transactional
class ProductNumberFactoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductNumberFactory productNumberFactory;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("다음 상품 번호를 구할 때 기존에 아무 상품이 없다면 값은 001이다.")
    @Test
    public void createNestProductNumberWithNoProduct() {
        // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(nextProductNumber).isEqualTo("001");
    }

    @DisplayName("다음 상품 번호는 마지막으로 등록된 상품의 상품 번호에서 1을 더한 값이다.")
    @Test
    public void createNestPro트ductNumber() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD, "라떼", 4500);
        Product product3 = createProduct("003", BAKERY, STOP_SELLING, "프라페", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(nextProductNumber).isEqualTo("004");
    }

    private static Product createProduct(String productNumber, ProductType productType, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(productType)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }

}