package me.woo.wmarket.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NicknameRequest {

  @Pattern(regexp = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{3,8}$", message = "3자 이상으로 적어주세요.")
  @NotEmpty(message = "닉네임을 입력해주세요.")
  private String nickname;
}
