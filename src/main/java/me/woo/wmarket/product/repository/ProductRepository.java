package me.woo.wmarket.product.repository;

import java.util.List;
import me.woo.wmarket.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
