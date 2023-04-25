package me.woo.wmarket.chatting.controller;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final ChatMessageService chatMessageService;

  @MessageMapping("/{roomId}")
  public void startChat() {

  }
}
