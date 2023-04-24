package me.woo.wmarket.product.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.woo.wmarket.product.dto.ProductRequest;
import me.woo.wmarket.product.dto.ProductResponse;
import me.woo.wmarket.product.service.ProductService;
import me.woo.wmarket.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping("/addition")
  public ResponseEntity<String> addProduct(@RequestBody ProductRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    productService.addProduct(request, userDetails.getUsername());
    return new ResponseEntity<>("상품 등록 완료", HttpStatus.CREATED);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
    return ResponseEntity.ok().body(productService.getProduct(productId));
  }

  @GetMapping("/list")
  public ResponseEntity<List<ProductResponse>> showProducts() {
    return ResponseEntity.ok().body(productService.showProducts());
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId) {
    return ResponseEntity.ok().body(productService.updateProduct(productId));
  }

  @PatchMapping("/{productId}")
  public ResponseEntity<ProductResponse> updateStatus(@PathVariable Long productId) {
    return ResponseEntity.ok().body(productService.updateStatus(productId));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
    return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
  }
}
