package com.example.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name="tbl_board")
public class BoardEntity extends BoardBaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private Long idx;
    @Column(columnDefinition="VARCHAR(20) NOT NULL COMMENT '아이디' COLLATE 'utf8mb4_unicode_ci'")
    private String user_id;
    @Column(columnDefinition="VARCHAR(200) NOT NULL COMMENT '글제목' COLLATE 'utf8mb4_unicode_ci'")
    private String title;
    @Column(columnDefinition="TEXT NOT NULL COMMENT '글내용' COLLATE 'utf8mb4_unicode_ci'")
    private String content;
    @Column(columnDefinition="DATE NOT NULL COMMENT '출력날짜' COLLATE 'utf8mb4_unicode_ci'")
    private LocalDate display_date;
    @Builder.Default
    @Min(0)
    @Column(columnDefinition="INT NULL DEFAULT '0' COMMENT '조회수'")
    private int read_cnt=0;

    public void modify(String title, String content, LocalDate display_date) {
        this.title = title;
        this.content = content;
        this.display_date = display_date;
        super.setUpdated_at(LocalDateTime.now());
    }
}
