package me.woo.wmarket.chatting.repository;

import me.woo.wmarket.chatting.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
