package me.woo.wmarket.product.dto;

import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.product.entity.Product.Category;

@Getter
@Builder
public class ProductUpdateRequest {

  private String title;
  private String content;
  private String image;
  private Long price;
  private Category category;

}
