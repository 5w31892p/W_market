package me.woo.wmarket.chatting.service;

import me.woo.wmarket.chatting.dto.MessageDetails;

public interface KafkaProducerService {
  void send(MessageDetails messageDetails);
}
