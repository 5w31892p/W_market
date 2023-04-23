package me.woo.wmarket.security;

import java.util.ArrayList;
import java.util.Collection;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.entity.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private final User user;
  private final String username;

  public UserDetailsImpl(User user, String username) {
    this.user = user;
    this.username = username;
  }

  public User getUser() {
    return user;
  }

  public Long getUserId() {
    return user.getId();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    UserRoleEnum role = user.getRole();
    String authority = role.getAuthority();

    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(simpleGrantedAuthority);

    return authorities;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
