package kr.idu.OInjo_Shop.entity.Mail;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String code;
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public MailEntity(String email, String code) {
        this.email = email;
        this.code = code;
        this.createdTime = LocalDateTime.now();
    }

    public void setCode(String code) {
        this.code = code;
        this.createdTime = LocalDateTime.now();
    }


    public String getCode() {
        return code;
    }
}
