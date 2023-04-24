package me.woo.wmarket.product.service;

import java.util.List;
import me.woo.wmarket.product.dto.ProductRequest;
import me.woo.wmarket.product.dto.ProductResponse;

public interface ProductService {

  void addProduct(ProductRequest request, String username);

  ProductResponse getProduct(Long productId);

  List<ProductResponse> showProducts();

  ProductResponse updateProduct(Long productId);

  ProductResponse updateStatus(Long productId);

  void deleteProduct(Long productId);

}
