package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.entity.Mail.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class EntityCleanupScheduler {
    private static final int CLEANUP_INTERVAL_MIN = 60 * 24 * 30; // 1달 (단위: 분)
    private final EntityManager entityManager;

    public EntityCleanupScheduler() {
        this.entityManager = null;
    }

    @Autowired
    public EntityCleanupScheduler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Scheduled(fixedDelay = CLEANUP_INTERVAL_MIN * 60 * 1000) // 주기적으로 실행되는 스케줄링 설정
    @Transactional
    public void cleanupEntities() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(CLEANUP_INTERVAL_MIN);

        // 시간이 지난 엔티티를 조회
        List<MailEntity> expiredEntities = entityManager.createQuery(
                        "SELECT e FROM MailEntity e WHERE e.createdTime <= :expirationTime", MailEntity.class)
                .setParameter("expirationTime", expirationTime)
                .getResultList();

        // 조회한 엔티티 삭제
        for (MailEntity expiredEntity : expiredEntities) {
            entityManager.remove(expiredEntity);
        }
    }
}
