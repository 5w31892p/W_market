package me.woo.wmarket.chatting.controller;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.service.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @PostMapping("/{productId}")
  public ResponseEntity<String> createRoom() {
    return new ResponseEntity<>("채팅방 생성 완료", HttpStatus.CREATED);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<String> getRoom() {
    return new ResponseEntity<>("채팅방 내용 슉슉", HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<String> showRoomList() {
    return new ResponseEntity<>("내 채팅리스트 슉슉", HttpStatus.OK);
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<String> deleteRoom() {
    return new ResponseEntity<>("삭제 완료", HttpStatus.NO_CONTENT);
  }

}
