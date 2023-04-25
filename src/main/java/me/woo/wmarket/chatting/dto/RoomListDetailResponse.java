package me.woo.wmarket.chatting.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomListDetailResponse {

  private Long userId;
  private Set<RoomListResponse> roomList;

}
