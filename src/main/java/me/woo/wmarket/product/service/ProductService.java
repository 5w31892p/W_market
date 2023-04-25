package me.woo.wmarket.product.service;

import java.util.List;
import me.woo.wmarket.product.dto.ProductRequest;
import me.woo.wmarket.product.dto.ProductResponse;
import me.woo.wmarket.product.dto.ProductUpdateRequest;
import me.woo.wmarket.product.dto.StatusUpdateRequest;

public interface ProductService {

  void addProduct(ProductRequest request, String username);

  ProductResponse getProduct(Long productId);

  List<ProductResponse> showProducts();

  ProductResponse updateProduct(Long productId, ProductUpdateRequest updateRequest, Long userId);

  ProductResponse updateStatus(Long productId, StatusUpdateRequest updateRequest, Long userId);

  void deleteProduct(Long productId, Long userId);

}
