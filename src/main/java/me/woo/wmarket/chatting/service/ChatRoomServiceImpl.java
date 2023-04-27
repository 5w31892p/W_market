package me.woo.wmarket.chatting.service;

import lombok.RequiredArgsConstructor;
import me.woo.wmarket.chatting.dto.RoomListDetailResponse;
import me.woo.wmarket.chatting.dto.RoomResponse;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.chatting.repository.ChatMessageRepository;
import me.woo.wmarket.chatting.repository.ChatRoomRepository;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.product.repository.ProductRepository;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

  private final ChatRoomRepository chatRoomRepository;
  private final ChatMessageRepository messageRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;


  @Override
  @Transactional
  public void createRoom(Long productId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원정보가 올바르지 않습니다.")
    );

    Product product = productRepository.findById(productId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
    );

    if (chatRoomRepository.existsByProductAndBuyer(product, user)) {
      throw new RuntimeException("해당 상품에 대한 채팅방이 이미 존재합니다.");
    }
    if (user.getId().equals(product.getSeller().getId())) {
      throw new RuntimeException("본인의 판매 상품에 채팅방을 개설할 수 없습니다.");
    }


    ChatRoom chatRoom = ChatRoom.builder()
        .seller(product.getSeller().getId())
        .buyer(user)
        .product(product)
        .build();

    chatRoomRepository.save(chatRoom);
  }

  @Override
  @Transactional(readOnly = true)
  public RoomResponse getRoom(Long roomId, String username) {
    ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 채팅방입니다.")
    );
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원정보가 올바르지 않습니다.")
    );

    if (!chatRoom.checkChatroomUser(user.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 속한 채팅만 볼 수 있습니다.");
    }
    return new RoomResponse(chatRoom, user);
  }

  @Override
  @Transactional(readOnly = true)
  public RoomListDetailResponse showRoomList(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원정보가 올바르지 않습니다.")
    );
    return new RoomListDetailResponse(user);
  }

  @Override
  @Transactional
  public void deleteRoom(Long roomId, String username) {
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원정보가 올바르지 않습니다.")
    );
    ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 채팅방입니다.")
    );


    if (!chatRoom.checkChatroomUser(user.getId()) || chatRoom.getSeller().equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 속한 채팅만 삭제할 수 있습니다.");
    }
    chatRoomRepository.deleteById(chatRoom.getId());
  }
}
