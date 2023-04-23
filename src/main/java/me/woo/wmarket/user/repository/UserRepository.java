package me.woo.wmarket.user.repository;

import java.util.Optional;
import me.woo.wmarket.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
