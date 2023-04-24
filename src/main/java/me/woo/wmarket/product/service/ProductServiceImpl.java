package me.woo.wmarket.product.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.woo.wmarket.product.dto.ProductRequest;
import me.woo.wmarket.product.dto.ProductResponse;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.product.repository.ProductRepository;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public void addProduct(ProductRequest request, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원정보가 올바르지 않습니다.")
    );
    Product product = Product.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .image(request.getImage())
        .price(request.getPrice())
        .category(request.getCategory())
        .status(request.getStatus())
        .seller(user)
        .build();
    productRepository.save(product);
  }

  @Override
  @Transactional
  public ProductResponse getProduct(Long productId) {
    Product product = productRepository.findById(productId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.")
    );
    return new ProductResponse(product);
  }

  @Override
  @Transactional
  public List<ProductResponse> showProducts() {
    List<Product> list = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    List<ProductResponse> productList = new ArrayList<>();
    list.forEach(product -> productList.add(new ProductResponse(product)));
    return productList;
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
