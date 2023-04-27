package me.woo.wmarket.chatting.dto;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.user.entity.User;

@Getter
@Builder
@AllArgsConstructor
public class RoomResponse {

  private Long roomId;
  private Long productId;
  private String productTitle;
  private String productImg;
  private Long productPrice;
  private String receiver;
  private Set<MessageDetails> message;

  public RoomResponse(ChatRoom chatRoom, User user) {
    this.roomId = chatRoom.getId();
    this.productId = chatRoom.getProduct().getId();
    this.productTitle = chatRoom.getProduct().getTitle();
    this.productImg = chatRoom.getProduct().getImage();
    this.productPrice = chatRoom.getProduct().getPrice();
    if (chatRoom.getProduct().getSeller().getId().equals(user.getId())) {
      // 판매자인 경우
      this.receiver = chatRoom.getBuyer().getNickname();
    } else {
      // 구매자인 경우
      this.receiver = chatRoom.getProduct().getSeller().getNickname();
    }

    this.message = chatRoom.getMessages().stream()
        .map(MessageDetails::new)
        .sorted(Comparator.comparing(MessageDetails::getSendTime))
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }
}
