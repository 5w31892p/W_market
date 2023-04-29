package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.woo.wmarket.chatting.dto.MessageDetails;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
  private final SimpMessagingTemplate template;

  @Override
  @Transactional
  @KafkaListener(topics = "topic", groupId = "chat-group")
  public void consume(MessageDetails message) {
    log.info("receive : " + message.getMessage());
    template.convertAndSend("/topic/" + message.getRoomId(), message);
  }
}
