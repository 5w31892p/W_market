package me.woo.wmarket.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignupRequest {

  private String email;
  private String password;
  private String nickname;

}
