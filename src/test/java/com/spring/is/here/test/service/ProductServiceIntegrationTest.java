package com.spring.is.here.test.service;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.spring.is.here.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;
import com.spring.is.here.repository.ProductRepository;
import com.spring.is.here.repository.ShopRepository;
import com.spring.is.here.service.ProductService;

public class ProductServiceIntegrationTest {
	@TestConfiguration
    static class ProductServiceTestContextConfiguration {
  
		@Autowired
		public ProductRepository productRepository;
		
		@Autowired
		public ShopRepository shopRepository;
		
        @Bean
        public ProductService productService() {
            return new ProductService(productRepository, shopRepository);
        }
    }
 
    @Autowired
    private ProductService productService;
 
    @MockBean
    private ProductRepository productRepository;
 
    @Before(value = "")
    public void setUp() {
		Product product = new Product("product", 1, "first product");
     
        Mockito.when(productRepository.findByName(product.getName()))
          .thenReturn(product);
    }
    
    @Test
    public void whenValidName_thenProductShouldBeFound() {
        String name = "product";
        Product found = productService.getProductByName(name);
      
         assertThat(found.getName())
          .isEqualTo(name);
     }
    
}
