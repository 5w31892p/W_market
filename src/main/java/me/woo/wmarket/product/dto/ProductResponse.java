package me.woo.wmarket.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.product.entity.Product.Category;
import me.woo.wmarket.product.entity.Product.TransactionStatus;

@Getter
@Builder
@AllArgsConstructor
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

  public ProductResponse(Product product) {
    this.productId = product.getId();
    this.sellerId = product.getSeller().getId();
    this.seller = product.getSeller().getNickname();
    this.title = product.getTitle();
    this.content = product.getContent();
    this.image = product.getImage();
    this.price = product.getPrice();
    this.category = product.getCategory();
    this.status = product.getStatus();
  }

}
