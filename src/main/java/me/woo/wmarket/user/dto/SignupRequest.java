package me.woo.wmarket.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequest {

  @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "영문과 숫자를 포함하여 4자 이상 12자 이하로 적어주세요.")
  @NotEmpty(message = "아이디를 입력해주세요.")
  private String username;

  @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-zA-Z]).{8,20}$", message = "영문과 숫자 그리고 특수문자를 포함하여 8자 이상 20자 이하로 적어주세요.")
  @NotEmpty(message = "비밀번호를 입력해주세요.")
  private String password;

  @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{3,15}$", message = "3자 이상으로 적어주세요.")
  @NotEmpty(message = "닉네임을 입력해주세요.")
  private String nickname;
  private boolean admin = false;

}
