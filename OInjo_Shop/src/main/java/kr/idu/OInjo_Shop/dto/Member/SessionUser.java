package kr.idu.OInjo_Shop.dto.Member;


import kr.idu.OInjo_Shop.entity.Member.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
//인증된 사용자 정보만 담기 위한 클래스
//엔티티를 주고 받을 때 직접적으로 사용하는 것보다 이렇게 Dto 클래스를 만들어 사용하는 것이 성능상 이점이 있음.


public class SessionUser implements Serializable {  //Serializable 직력화 기능을 가지게 해줌

    SessionUser() {}

    public SessionUser(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

    private String nickname;

    private String email;

    private String picture;

    private String gender;

    private String age;

}