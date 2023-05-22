package kr.idu.OInjo_Shop.entity.Mail;

import kr.idu.OInjo_Shop.service.EntityCleanupScheduler;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(EntityCleanupScheduler.class)
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String code;
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public MailEntity() {
        // Default constructor required by JPA
    }

    public MailEntity(String email, String code) {
        this.email = email;
        this.code = code;
        this.createdTime = LocalDateTime.now();
    }

    public void setCode(String code) {
        this.code = code;
        this.createdTime = LocalDateTime.now();
    }

}
