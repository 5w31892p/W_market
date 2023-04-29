package me.woo.wmarket.chatting.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.woo.wmarket.chatting.dto.MessageDetails;
import me.woo.wmarket.chatting.service.ChatMessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
  private final ChatMessageService chatMessageService;
  private final SimpMessagingTemplate template;

  @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
  public void receiveChatMessage(MessageDetails message) {
    template.convertAndSend("/sub/" + message.getReceiver() + "/" + message.getRoomId(), message);
  }
}
