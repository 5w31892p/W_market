package me.woo.wmarket.chatting.repository;

import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
  boolean existsByProductAndBuyer(Product product, User user);
}
