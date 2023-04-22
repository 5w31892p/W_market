package me.woo.wmarket.product.repository;

import me.woo.wmarket.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    }
