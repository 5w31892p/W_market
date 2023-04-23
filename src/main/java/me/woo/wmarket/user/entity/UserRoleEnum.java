package me.woo.wmarket.user.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

  ADMIN(Authority.ADMIN),
  MEMBER(Authority.MEMBER);

  private final String authority;

  UserRoleEnum(String authority) {
    this.authority = authority;
  }

  public String getAuthority() {
    return this.authority;
  }

  public static  class Authority {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String MEMBER = "ROLE_MEMBER";

  }

}
