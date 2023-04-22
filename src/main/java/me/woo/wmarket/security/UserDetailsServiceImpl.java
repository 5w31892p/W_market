package me.woo.wmarket.security;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자입니다."));

    return new UserDetailsImpl(user);
  }
}
