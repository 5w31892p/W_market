package me.woo.wmarket.config;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.jwtUtil.JwtUtil;
import me.woo.wmarket.security.JwtAuthFilter;
import me.woo.wmarket.security.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtUtil jwtUtil;
  private final UserDetailsServiceImpl userDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeHttpRequests().requestMatchers("/users/**").permitAll()
        .requestMatchers("/products/**").permitAll()
        .requestMatchers("/chatroom/**").permitAll()
        .requestMatchers("/ws/**").permitAll()
        .requestMatchers("/ws/topic/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new JwtAuthFilter(jwtUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public WebSecurityCustomizer WebSecurityCustomizer() {
    return (web -> web.ignoring()
        .requestMatchers(PathRequest.toH2Console())
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean // 인증담당
  AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
