package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

  private final ChatRoomRepository chatRoomRepository;


  @Override
  public void createRoom() {

  }

  @Override
  public void getRoom() {

  }

  @Override
  public void showRoomList() {

  }

  @Override
  public void deleteRoom() {

  }
}
