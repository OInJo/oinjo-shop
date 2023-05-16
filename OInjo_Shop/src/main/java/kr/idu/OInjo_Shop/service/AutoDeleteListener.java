package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.entity.Mail.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class AutoDeleteListener {
    private static final int EXPIRATION_MIN= 1; // 삭제 대기 시간 (예: 3분)
    private EntityManager entityManager; // JPA 엔티티 매니저를 관리 & 영속성 컨텍스트와 상호작용
    // 엔티티를 생성, 조회, 삭제 ,수정 등의 작업에 사용

    @PreRemove
    @Transactional // 트랜잭션 시작
    public void preRemove(Object entity) {
        if (entity instanceof MailEntity) {
            MailEntity mailEntity = (MailEntity) entity;
            LocalDateTime expirationTime = mailEntity.getCreatedTime().plusMinutes(EXPIRATION_MIN);

            if (LocalDateTime.now().isAfter(expirationTime)) {
                // 시간이 지나면 삭제 로직 수행
                // 예: 데이터베이스에서 해당 엔티티 삭제
                entityManager.remove(mailEntity);
                entityManager.flush(); // 변경사항 즉시 DB에 즉시 반영
            }
        }
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
