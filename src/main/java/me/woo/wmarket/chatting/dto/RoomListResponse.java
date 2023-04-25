package me.woo.wmarket.chatting.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomListResponse {

  private Long roomId;
  private Long productId;
  private String receiver;
  private String lastMessage;
  private LocalDateTime lastSendTime;

}
