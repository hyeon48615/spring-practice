package net.fullstack.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true) // 상위 클래스의 프로퍼티도 빌더에 포함 --> 클래스명.builder().......build();
public abstract class BbsBaseEntity {
    @CreatedDate
    @Column(name="created_at", nullable = false, insertable = true, updatable = false,
            columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(name="updated_at", nullable = true, insertable = false, updatable = true,
            columnDefinition = "DATETIME NULL DEFAULT NULL COMMENT '수정일'"
    )
    private LocalDateTime updated_at;

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}