package me.woo.wmarket.user.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  @Transactional
  public void signup(SignupRequest request) {
    String email = request.getEmail();
    String password = request.getPassword();
    String nickname = request.getNickname();

    User user = User.builder()
        .email(email)
        .password(password)
        .nickname(nickname)
        .build();
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void signin(SigninRequest request) {
    String email = request.getEmail();
    String password = request.getPassword();

    // 사용자 확인
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.")
    );

    // 비밀번호 확인
    if (!password.equals(user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.");
    }
  }
}
