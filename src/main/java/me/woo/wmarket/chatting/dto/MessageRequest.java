package me.woo.wmarket.chatting.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.chatting.entity.ChatMessage;

@Getter
@Builder
@AllArgsConstructor
public class MessageRequest {

  private String sender;
  private String receiver;
  private String message;
  private LocalDateTime sendTime;

  public MessageRequest(ChatMessage chatMessage) {
    this.sender = chatMessage.getSender();
    this.receiver = chatMessage.getReceiver();
    this.message = chatMessage.getMessage();
    this.sendTime = chatMessage.getSendTime();
  }

}
