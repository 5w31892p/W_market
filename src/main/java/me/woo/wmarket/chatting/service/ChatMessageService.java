package me.woo.wmarket.chatting.service;

import me.woo.wmarket.chatting.dto.MessageDetails;

public interface ChatMessageService {

  MessageDetails startChat(Long roomId, MessageDetails message);

}
