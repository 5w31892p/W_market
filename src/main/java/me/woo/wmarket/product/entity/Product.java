package me.woo.wmarket.product.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woo.wmarket.chatting.entity.ChatRoom;
import me.woo.wmarket.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Product {
  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private Long price;

  @Enumerated(EnumType.STRING)
  private Category category;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;


  public enum Category {
    IT,
    CAR,
    CLOTHING,
    ACCESSORY,
    FOOD,
    FURNITURE
  }

  public enum TransactionStatus{
    판매,
    나눔,
    예약중,
    거래완료
  }

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  @Builder
  public Product(String title, String content, String image, Long price, Category category,
      TransactionStatus status, User seller) {
    this.title = title;
    this.content = content;
    this.image = image;
    this.price = price;
    this.category = category;
    this.status = status;
    this.seller = seller;
  }

  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @ManyToOne
  @JoinColumn(name = "seller_id")
  private User seller;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ChatRoom> chatRoom = new LinkedHashSet<>();


  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */


  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */
}
