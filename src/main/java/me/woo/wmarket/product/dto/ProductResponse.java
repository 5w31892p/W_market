package me.woo.wmarket.product.dto;

import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.product.entity.Product.Category;
import me.woo.wmarket.product.entity.Product.TransactionStatus;

@Getter
@Builder
public class ProductResponse {

  private Long productId;
  private Long sellerId;
  private String seller;
  private String title;
  private String image;
  private String content;
  private Long price;
  private Category category;
  private TransactionStatus status;

}
