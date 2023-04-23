package me.woo.wmarket.user.service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.woo.wmarket.jwtUtil.JwtUtil;
import me.woo.wmarket.user.dto.NicknameRequest;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.entity.UserRoleEnum;
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
    Optional<User> users = userRepository.findByUsername(request.getUsername());
    if (users.isPresent()) { throw new IllegalArgumentException("존재하는 유저입니다."); }
    String username = request.getUsername();
    String password = passwordEncoder.encode(request.getPassword());
    String nickname = request.getNickname();
    UserRoleEnum role = UserRoleEnum.MEMBER;

    User user = User.builder()
        .username(username)
        .password(password)
        .nickname(nickname)
        .role(role)
        .build();
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void signin(SigninRequest request, HttpServletResponse response) {
    String username = request.getUsername();
    String password = request.getPassword();

    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.")
    );

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "회원정보가 일치하지 않습니다.");
    }

    String token = jwtUtil.createToken(user.getUsername(), user.getRole());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
  }

  @Override
  @Transactional
  public void updateNickname(NicknameRequest request, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.")
    );

    if (!user.checkAuthorization(user)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
    }

    String nickname = request.getNickname();

    user.updateNickname(nickname);
    this.userRepository.save(user);

  }

  @Override
  @Transactional
  public void deleteUser(Long userId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "회원정보가 일치하지 않습니다.")
    );

    if (!user.checkAuthorization(user)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
    }
    userRepository.deleteById(userId);
  }
}
