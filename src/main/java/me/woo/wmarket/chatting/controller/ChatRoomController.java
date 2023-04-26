package me.woo.wmarket.chatting.controller;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.dto.RoomListDetailResponse;
import me.woo.wmarket.chatting.dto.RoomResponse;
import me.woo.wmarket.chatting.service.ChatRoomService;
import me.woo.wmarket.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @PostMapping("/{productId}")
  public ResponseEntity<String> createRoom(@PathVariable Long productId, @AuthenticationPrincipal
      UserDetailsImpl userDetails) {
    chatRoomService.createRoom(productId, userDetails.getUsername());
    return new ResponseEntity<>("채팅방 생성 완료", HttpStatus.CREATED);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomResponse> getRoom(@PathVariable Long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.ok().body(chatRoomService.getRoom(roomId, userDetails.getUsername()));
  }

  @GetMapping("/list")
  public ResponseEntity<RoomListDetailResponse> showRoomList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return ResponseEntity.ok().body(chatRoomService.showRoomList(userDetails.getUsername()));
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<String> deleteRoom(@PathVariable Long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    chatRoomService.deleteRoom(roomId, userDetails.getUsername());
    return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
  }

}
