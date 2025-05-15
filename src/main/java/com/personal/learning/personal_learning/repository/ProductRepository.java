// repository/ProductRepository.java
package com.personal.learning.personal_learning.repository;

import com.personal.learning.personal_learning.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
