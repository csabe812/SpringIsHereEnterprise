package com.spring.is.here.test.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.is.here.domain.Product;
import com.spring.is.here.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ProductRepository productRepository;

	
	@Test
	public void whenFindByName_thenReturnEmployee() {
	    // given
		Product product = new Product("product", 1, "first product");
	    entityManager.persist(product);
	    entityManager.flush();
	 
	    // when
	    Product found = productRepository.findByName(product.getName());
	 
	    // then
	    assertThat(found.getName()).isEqualTo(product.getName());
	}
	
}
