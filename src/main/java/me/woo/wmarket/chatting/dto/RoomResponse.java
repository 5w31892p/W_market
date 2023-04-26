package me.woo.wmarket.chatting.dto;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.chatting.entity.ChatRoom;

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
  private Set<MessageRequest> message;

  public RoomResponse(ChatRoom chatRoom) {
    this.roomId = chatRoom.getId();
    this.productId = chatRoom.getProduct().getId();
    this.productTitle = chatRoom.getProduct().getTitle();
    this.productImg = chatRoom.getProduct().getImage();
    this.productPrice = chatRoom.getProduct().getPrice();
    this.receiver = chatRoom.getProduct().getSeller().getId().equals(chatRoom.getSeller())
        ? chatRoom.getProduct().getSeller().getNickname()
        : chatRoom.getBuyer().getNickname();
    this.message = chatRoom.getMessages().stream()
        .map(MessageRequest::new)
        .sorted(Comparator.comparing(MessageRequest::getSendTime))
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

}
