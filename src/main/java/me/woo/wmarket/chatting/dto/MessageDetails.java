package me.woo.wmarket.chatting.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woo.wmarket.chatting.entity.ChatMessage;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetails {
  private Long roomId;
  private String sender;
  private String receiver;
  private String message;
  private LocalDateTime sendTime;

  public MessageDetails(ChatMessage chatMessage) {
    this.roomId = chatMessage.getChatRoom().getId();
    this.sender = chatMessage.getSender();
    this.receiver = chatMessage.getReceiver();
    this.message = chatMessage.getMessage();
    this.sendTime = chatMessage.getSendTime();
  }

}
