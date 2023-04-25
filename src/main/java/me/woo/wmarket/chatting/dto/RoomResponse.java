package me.woo.wmarket.chatting.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {

  private Long roomId;
  private Long productId;
  private String sender;
  private String receiver;
  private String message;
  private LocalDateTime sendDate;

}
