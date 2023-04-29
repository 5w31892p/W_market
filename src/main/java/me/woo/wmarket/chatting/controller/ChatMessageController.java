package me.woo.wmarket.chatting.controller;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.dto.MessageDetails;
import me.woo.wmarket.chatting.service.KafkaProducerService;
import me.woo.wmarket.chatting.service.ChatMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

  private final ChatMessageService chatMessageService;
  private final KafkaProducerService kafkaProducer;

  @MessageMapping("/{roomId}")
  public ResponseEntity<MessageDetails> startChat(@DestinationVariable Long roomId, MessageDetails message) {
    // 메시지 전송
    kafkaProducer.send(message);
    return ResponseEntity.ok().body(chatMessageService.startChat(roomId, message));
  }
}
