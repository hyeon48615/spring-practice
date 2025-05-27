package net.fullstack.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tbl_board")
@SuperBuilder(toBuilder = true) // 상위 클래스의 프로퍼티도 빌더에 포함 --> 클래스명.builder().......build();
public class BbsEntity extends BbsBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long idx;
    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '아이디' COLLATE 'utf8mb4_unicode_ci'")
    private String user_id;
    @Column(columnDefinition = "VARCHAR(200) NOT NULL COMMENT '글제목' COLLATE 'utf8mb4_unicode_ci'")
    private String title;
    @Column(columnDefinition = "TEXT NOT NULL COMMENT '글내용' COLLATE 'utf8mb4_unicode_ci'")
    private String content;
    @Column(columnDefinition = "CHAR(10) NULL COMMENT '출력날짜' COLLATE 'utf8mb4_unicode_ci'")
    private String display_date;
    @Builder.Default
    @Min(0)
    @Column(columnDefinition = "INT NULL DEFAULT '0' COMMENT '조회수'")
    private int read_cnt=0;

    public void modify(String title, String content, String display_date) {
        this.title = title;
        this.content = content;
        this.display_date = display_date;
        super.setUpdated_at(LocalDateTime.now());
    }
}