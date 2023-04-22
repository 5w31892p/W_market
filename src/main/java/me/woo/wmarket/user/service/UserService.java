package me.woo.wmarket.user.service;

import me.woo.wmarket.user.dto.SigninRequest;
import me.woo.wmarket.user.dto.SignupRequest;

public interface UserService {

  void signup(SignupRequest request);

  void signin(SigninRequest request);

}
