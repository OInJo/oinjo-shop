package kr.idu.OInjo_Shop.entity.Member;



import kr.idu.OInjo_Shop.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Table(name = "snsUser")
public class User {

    //네이버 인증 후 가져올 정보

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;

    @Column
    private String email;
//
//    @Column(name = "profile_picture")
//    private String picture;
//
//    @Column
//    private String gender;
//
//    @Column
//    private String age; // 연령대

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public User update(String nickname) {       //수정
        this.nickname = nickname;
//        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
