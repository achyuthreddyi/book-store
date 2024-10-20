package com.achyuthreddyyi.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.achyuthreddyyi.catalog_service.TestcontainersConfiguration;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

// Slice test
@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            // "spring.datasource.url=jdbc:tc:postgresql:17-alpine://db"
        })
@Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();

        assertThat(products).hasSize(15);
    }

    @Test
    void shouldGetProductById() {
        ProductEntity productEntity = productRepository.findByCode("P100").orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo("P100");
        assertThat(productEntity.getName()).isEqualTo("The Hunger Games");
        assertThat(productEntity.getDescription())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(productRepository.findByCode("Invalid product Id")).isEmpty();
    }
}
