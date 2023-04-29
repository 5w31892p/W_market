package me.woo.wmarket.chatting.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.woo.wmarket.chatting.dto.MessageDetails;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
  private final KafkaTemplate<String, MessageDetails> kafkaTemplate;
  private static final String TOPIC = "topic";

  public void send(MessageDetails messageDetails) {
    log.info("send : " + messageDetails.getMessage());
    kafkaTemplate.send(TOPIC, messageDetails);
  }
}
