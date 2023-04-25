package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

  private final ChatMessageRepository chatMessageRepository;


  @Override
  public void startChat() {

  }
}
