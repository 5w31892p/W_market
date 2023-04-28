package me.woo.wmarket.product.service;

import static me.woo.wmarket.product.entity.Product.Category.CLOTHING;
import static me.woo.wmarket.product.entity.Product.Category.IT;
import static me.woo.wmarket.product.entity.Product.TransactionStatus.거래완료;
import static me.woo.wmarket.product.entity.Product.TransactionStatus.판매;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.woo.wmarket.product.dto.ProductRequest;
import me.woo.wmarket.product.dto.ProductResponse;
import me.woo.wmarket.product.dto.ProductUpdateRequest;
import me.woo.wmarket.product.dto.StatusUpdateRequest;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.product.repository.ProductRepository;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  ProductRepository  productRepository;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  ProductServiceImpl productService;

  @Test
  @DisplayName("상품등록 성공")
  void addProduct() {
    // given
    User user = mock(User.class);

    ProductRequest product = ProductRequest.builder()
        .title("행거 팔아요")
        .content("쿠팡에서 40 주고 샀어요. 3개월 사용 했습니다.")
        .image("aaaa.jpg")
        .price(150000L)
        .category(CLOTHING)
        .status(판매)
        .build();

    given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
    // when
    productService.addProduct(product, user.getUsername());

    // then
    verify(productRepository, times(1)).save(any(Product.class));
  }

  @Test
  @DisplayName("상품 불러오기 성공")
  void getProduct() {
    // given
    User user = mock(User.class);
    Product product = Product.builder()
        .title("행거 팔아요")
        .content("쿠팡에서 40 주고 샀어요. 3개월 사용 했습니다.")
        .image("aaaa.jpg")
        .price(150000L)
        .category(CLOTHING)
        .status(판매)
        .seller(user)
        .build();

    given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
    ProductResponse response = productService.getProduct(product.getId());

    // when
    assertThat(response).isNotNull();
    assertThat(response.getTitle()).isEqualTo(product.getTitle());
    assertThat(response.getContent()).isEqualTo(product.getContent());
    assertThat(response.getPrice()).isEqualTo(product.getPrice());

    // then
  }

  @Test
  @DisplayName("전체상품 리스트 성공")
  void showProducts() {
    // given
    List<Product> productList = new ArrayList<>();

    User user = mock(User.class);
    Product product1 = Product.builder()
        .title("행거 팔아요")
        .content("쿠팡에서 40 주고 샀어요. 3개월 사용 했습니다.")
        .image("aaaa.jpg")
        .price(150000L)
        .category(CLOTHING)
        .status(판매)
        .seller(user)
        .build();

    Product product2 = Product.builder()
        .title("노트북 팔아요")
        .content("A급")
        .image("pc.jpg")
        .price(200000L)
        .category(IT)
        .status(판매)
        .seller(user)
        .build();
    productList.add(product1);
    productList.add(product2);
    given(productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))).willReturn(productList);

    // when
    List<ProductResponse> productResponseList = productService.showProducts();

    // then
    assertEquals(2, productResponseList.size());
  }

  @Test
  @DisplayName("상품 업데이트 성공")
  void updateProduct() {
    // given
    User user = mock(User.class);
    Product product = Product.builder()
        .title("행거 팔아요")
        .content("쿠팡에서 40 주고 샀어요. 3개월 사용 했습니다.")
        .image("aaaa.jpg")
        .price(150000L)
        .category(CLOTHING)
        .status(판매)
        .seller(user)
        .build();
    ProductUpdateRequest updateRequest = ProductUpdateRequest.builder()
        .title("행거말고 옷걸이")
        .content("쿠팡말고 다이소")
        .image("bbb.jpg")
        .price(100L)
        .category(CLOTHING)
        .build();

    given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

    // when
    productService.updateProduct(product.getId(), updateRequest, user.getId());

    // then
    assertThat(product.getTitle()).isEqualTo(updateRequest.getTitle());
    assertThat(product.getContent()).isEqualTo(updateRequest.getContent());
    assertThat(product.getImage()).isEqualTo(updateRequest.getImage());
    assertThat(product.getPrice()).isEqualTo(updateRequest.getPrice());
    assertThat(product.getCategory()).isEqualTo(updateRequest.getCategory());
    verify(productRepository, times(1)).save(product);
  }

  @Test
  @DisplayName("거래상태 업데이트 성공")
  void updateStatus() {
    // given
    User user = mock(User.class);
    Product product = Product.builder()
        .title("행거 팔아요")
        .content("쿠팡에서 40 주고 샀어요. 3개월 사용 했습니다.")
        .image("aaaa.jpg")
        .price(150000L)
        .category(CLOTHING)
        .status(판매)
        .seller(user)
        .build();
    StatusUpdateRequest statusUpdateRequest = StatusUpdateRequest.builder()
        .status(거래완료)
        .build();
    given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

    // when
    productService.updateStatus(product.getId(), statusUpdateRequest, user.getId());



    // then
    assertThat(product.getStatus()).isEqualTo(statusUpdateRequest.getStatus());
    verify(productRepository, times(1)).save(product);
  }

  @Test
  @DisplayName("상품 삭제 성공")
  void deleteProduct() {
    // given
    User user = mock(User.class);
    Product product = mock(Product.class);


    given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
    given(product.checkSeller(user.getId())).willReturn(true);

    // when
    productService.deleteProduct(product.getId(), user.getId());

    // then
    verify(productRepository, times(1)).deleteById(product.getId());

  }
}