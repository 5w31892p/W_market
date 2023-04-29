package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.woo.wmarket.chatting.dto.MessageDetails;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService{
  private final KafkaTemplate<String, MessageDetails> kafkaTemplate;
  private static final String TOPIC = "topic";

  @Override
  @Transactional
  public void send(MessageDetails messageDetails) {

    log.info("send : " + messageDetails.getMessage());
    kafkaTemplate.send(TOPIC, messageDetails);
  }
}
