package me.woo.wmarket.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninRequest {
  private String username;
  private String password;

}
