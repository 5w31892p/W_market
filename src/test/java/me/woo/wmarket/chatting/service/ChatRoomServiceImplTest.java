package me.woo.wmarket.chatting.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.chatting.repository.ChatRoomRepository;
import me.woo.wmarket.product.entity.Product;
import me.woo.wmarket.product.repository.ProductRepository;
import me.woo.wmarket.user.entity.User;
import me.woo.wmarket.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatRoomServiceImplTest {

  @Mock
  ChatRoomRepository chatRoomRepository;

  @Mock
  UserRepository userRepository;

  @Mock
  ProductRepository productRepository;

  @InjectMocks
  ChatRoomServiceImpl chatRoomService;

  @Test
  @DisplayName("채팅방 생성 성공")
  void createRoom() {
    // given
    User buyer = User.builder()
        .id(1L)
        .username("user1")
        .build();
    User seller = User.builder()
        .id(2L)
        .username("user2")
        .build();
    Product product = Product.builder()
        .id(1L)
        .seller(seller)
        .build();

    given(userRepository.findByUsername(buyer.getUsername())).willReturn(Optional.of(buyer));
    given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
    given(chatRoomRepository.existsByProductAndBuyer(product, buyer)).willReturn(false);

    // when
      chatRoomService.createRoom(product.getId(), buyer.getUsername());

    // then
    verify(chatRoomRepository, times(1)).save(any(ChatRoom.class));
  }


  @Test
  @DisplayName("채팅방 불러오기 성공")
  void getRoom() {
    // given

    // when

    // then
  }

  @Test
  @DisplayName("채팅리스트 불러오기 성공")
  void showRoomList() {
    // given

    // when

    // then

  }
  @Test
  @DisplayName("채팅삭제 성공")
  void deleteRoom() {
    // given
    User user = User.builder()
        .id(1L)
        .username("user1")
        .nickname("hhi")
        .build();
    ChatRoom chatRoom = mock(ChatRoom.class);

    given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
    given(chatRoomRepository.findById(chatRoom.getId())).willReturn(Optional.of(chatRoom));
    given(chatRoom.checkChatroomUser(user.getId())).willReturn(true);


    // when
    chatRoomService.deleteRoom(chatRoom.getId(), user.getUsername());

    // then
    verify(chatRoomRepository, times(1)).deleteById(chatRoom.getId());

  }
}