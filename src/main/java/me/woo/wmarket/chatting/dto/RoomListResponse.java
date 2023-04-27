package me.woo.wmarket.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.user.entity.User;

@Getter
@Builder
@AllArgsConstructor
public class RoomListResponse {

  private Long roomId;
  private Long productId;
  private String productTitle;
  private String receiver;
  private boolean isSeller;


  public RoomListResponse(ChatRoom chatRoom, User user) {
    this.roomId = chatRoom.getId();
    this.productId = chatRoom.getProduct().getId();
    this.productTitle = chatRoom.getProduct().getTitle();
    this.isSeller = chatRoom.getProduct().getSeller().getId().equals(user.getId()); // 판매자 여부 설정

    if (isSeller) {
      // 판매자인 경우
      this.receiver = chatRoom.getBuyer().getNickname();
    } else {
      // 구매자인 경우
      this.receiver = chatRoom.getProduct().getSeller().getNickname();
    }
  }
}
