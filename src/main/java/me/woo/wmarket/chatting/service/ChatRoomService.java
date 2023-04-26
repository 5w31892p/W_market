package me.woo.wmarket.chatting.service;

import me.woo.wmarket.chatting.dto.RoomListDetailResponse;
import me.woo.wmarket.chatting.dto.RoomResponse;

public interface ChatRoomService {

  void createRoom(Long productId, String username);

  RoomResponse getRoom(Long roomId, String username );

  RoomListDetailResponse showRoomList(String username);

  void deleteRoom(Long roomId, String username);

}
