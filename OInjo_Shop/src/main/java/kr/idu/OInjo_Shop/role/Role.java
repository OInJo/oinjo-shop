package kr.idu.OInjo_Shop.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//사용자 권한 관리를 위한 클래스
//※스프링 시큐리티에서 권한 코드에 항상 맨 앞에 ROLE_이 있어야 함.
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "사용자");

    private final String key;
    private final String value;
}