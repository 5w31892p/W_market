package me.woo.wmarket.user.service;

import jakarta.servlet.http.HttpServletResponse;
import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;

public interface UserService {

  void signup(SignupRequest request);

  void signin(SigninRequest request, HttpServletResponse response);

  void deleteUser(Long userId, String email);

}
