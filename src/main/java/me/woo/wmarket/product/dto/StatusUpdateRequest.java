package me.woo.wmarket.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woo.wmarket.product.entity.Product.TransactionStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {

  private TransactionStatus status;

}
