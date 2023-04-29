package me.woo.wmarket.chatting.service;

import me.woo.wmarket.chatting.dto.MessageDetails;

public interface KafkaConsumerService {
  void consume(MessageDetails message);
}
