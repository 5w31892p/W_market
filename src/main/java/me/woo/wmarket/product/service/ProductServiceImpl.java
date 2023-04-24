package me.woo.wmarket.product.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.product.dto.ProductResponse;
import me.woo.wmarket.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;

  @Override
  public void addProduct() {

  }

  @Override
  public ProductResponse getProduct(Long productId) {
    return null;
  }

  @Override
  public ProductResponse showProducts() {
    return null;
  }

  @Override
  public ProductResponse updateProduct(Long productId) {
    return null;
  }

  @Override
  public ProductResponse updateStatus(Long productId) {
    return null;
  }

  @Override
  public void deleteProduct(Long productId) {

  }
}
