package me.woo.wmarket.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SigninRequest {
  private String username;
  private String password;

}
