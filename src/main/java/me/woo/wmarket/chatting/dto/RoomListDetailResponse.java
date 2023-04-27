package me.woo.wmarket.chatting.dto;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.user.entity.User;

@Getter
@Builder
public class RoomListDetailResponse {

  private Long userId;
  private Set<RoomListResponse> roomList;

  public RoomListDetailResponse(User user) {
    this.userId = user.getId();
    Set<RoomListResponse> roomList = new LinkedHashSet<>();
    // 판매자인 경우
    if (!user.getProduct().isEmpty()) {
      for (Product product : user.getProduct()) {
        for (ChatRoom chatRoom : product.getChatRooms()) {
          roomList.add(new RoomListResponse(chatRoom, user));
        }
      }
    }
    // 구매자인 경우
    else {
      for (ChatRoom chatRoom : user.getChatRooms()) {
        roomList.add(new RoomListResponse(chatRoom, user));
        if (chatRoom.getProduct().getSeller().getId() == user.getId()) {
          roomList.add(new RoomListResponse(chatRoom, chatRoom.getBuyer()));
        }
      }
    }
  }

}
