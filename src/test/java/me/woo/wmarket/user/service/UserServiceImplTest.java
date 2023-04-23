package me.woo.wmarket.user.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import me.woo.wmarket.jwtUtil.JwtUtil;
import me.woo.wmarket.user.dto.NicknameRequest;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.entity.UserRoleEnum;
import me.woo.wmarket.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @Mock
  JwtUtil jwtUtil;

  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  @DisplayName("회원가입 성공")
  void signup() {
    // given
    SignupRequest request = SignupRequest.builder()
        .username("user1")
        .nickname("nick1")
        .password("password12!")
        .build();

    // when
    userService.signup(request);

    // then
    verify(userRepository, times(1)).save(any());

  }

  @Test
  @DisplayName("로그인 성공")
  void signin() {
    // given
    SigninRequest request = SigninRequest.builder()
        .username("user1")
        .password("password12!")
        .build();

    String username = request.getUsername();
    String password = request.getPassword();

    User user = User.builder()
        .username(username)
        .password(password)
        .build();

    given(userRepository.findByUsername(username)).willReturn(Optional.of(user));
    given(!passwordEncoder.matches(password, user.getPassword())).willReturn(true);
    MockHttpServletResponse servletResponse = new MockHttpServletResponse();

    // when
    userService.signin(request, servletResponse);
    String token = jwtUtil.createToken(username, user.getRole());
    servletResponse.addHeader("Authorization", token);
    // then

  }

  @Test
  @DisplayName("닉네임 수정 성공")
  void updateNickname() {
    // given
    SignupRequest request = SignupRequest.builder()
        .username("user1")
        .nickname("newNick")
        .password("password12!")
        .build();

    NicknameRequest nicknameRequest = NicknameRequest.builder()
        .nickname("nickFix")
        .build();
    String nickname = nicknameRequest.getNickname();

    User user = User.builder()
        .username(request.getUsername())
        .password(request.getPassword())
        .nickname(nickname)
        .role(UserRoleEnum.MEMBER)
        .build();

    given(userRepository.findByUsername(anyString())).willReturn(Optional.of(user));


    // when
    userService.updateNickname(nicknameRequest, user.getNickname());

    // then
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  @DisplayName("탈퇴 성공")
  void deleteUser() {
    // given
    SignupRequest request = SignupRequest.builder()
        .username("user1")
        .nickname("newNick")
        .password("password12!")
        .build();

    User user = User.builder()
        .username(request.getUsername())
        .password(request.getPassword())
        .nickname(request.getNickname())
        .role(UserRoleEnum.MEMBER)
        .build();

    given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));

    // when
    userService.deleteUser(user.getId(), user.getUsername());

    // then
    verify(userRepository, times(1)).deleteById(user.getId());

  }
}