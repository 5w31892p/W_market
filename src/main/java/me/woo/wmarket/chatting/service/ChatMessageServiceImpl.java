package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.dto.MessageDetails;
import me.woo.wmarket.chatting.entity.ChatMessage;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.chatting.repository.ChatMessageRepository;
import me.woo.wmarket.chatting.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{

  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomRepository chatRoomRepository;


  @Override
  public MessageDetails startChat(Long roomId, MessageDetails message) {
    ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(
        () -> new IllegalArgumentException("채팅방이 존재하지 않습니다.")
    );
    ChatMessage chat = ChatMessage.builder()
        .sender(message.getSender())
        .receiver(message.getReceiver())
        .message(message.getMessage())
        .chatRoom(room)
        .build();

    chatMessageRepository.save(chat);

    return message;
  }
}
