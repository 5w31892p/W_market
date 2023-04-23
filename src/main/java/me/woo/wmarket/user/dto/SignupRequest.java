package me.woo.wmarket.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

  private String username;
  private String password;
  private String nickname;
  private boolean admin = false;

}
