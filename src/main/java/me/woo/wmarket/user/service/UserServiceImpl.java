package me.woo.wmarket.user.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.woo.wmarket.jwtUtil.JwtUtil;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @Override
  @Transactional
  public void signup(SignupRequest request) {
    String email = request.getEmail();
    String password = passwordEncoder.encode(request.getPassword());
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
  public void signin(SigninRequest request, HttpServletResponse response) {
    String email = request.getEmail();
    String password = request.getPassword();

    // 사용자 확인
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.")
    );

    // 비밀번호 확인
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.");
    }

    String token = jwtUtil.createToken(user.getNickname(), email);
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }

  @Override
  @Transactional
  public void deleteUser(Long userId, String email) {

  }
}
