package kr.idu.OInjo_Shop.entity;



import kr.idu.OInjo_Shop.Role.Role;
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

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(name = "profile_picture")
    private String picture;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String age; // 연령대

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User update(String nickname, String picture) {       //수정
        this.nickname = nickname;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
