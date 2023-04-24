package me.woo.wmarket.product.service;

import me.woo.wmarket.product.dto.ProductResponse;

public interface ProductService {

  void addProduct();

  ProductResponse getProduct(Long productId);

  ProductResponse showProducts();

  ProductResponse updateProduct(Long productId);

  ProductResponse updateStatus(Long productId);

  void deleteProduct(Long productId);

}
