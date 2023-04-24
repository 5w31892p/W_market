package me.woo.wmarket.product.dto;

import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.product.entity.Product.TransactionStatus;

@Getter
@Builder
public class StatusUpdateRequest {

  private TransactionStatus status;

}
