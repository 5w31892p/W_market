package me.woo.wmarket.user.controller;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;
import me.woo.wmarket.user.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  // 회원가입
  @PostMapping("/signup")
  public ResponseEntity<String> signup (@Validated @RequestBody SignupRequest request) {
    userService.signup(request);
    return new ResponseEntity<>("회원가입 완료", HttpStatus.CREATED);
  }

  // 로그인
  @PostMapping("/signin")
  public ResponseEntity<String> signin (@RequestBody SigninRequest request) {
    userService.signin(request);
    return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
  }
}
