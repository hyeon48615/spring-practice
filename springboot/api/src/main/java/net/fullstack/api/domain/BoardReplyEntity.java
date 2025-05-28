package net.fullstack.api.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Table(
    name="tbl_board_reply",
    indexes = {@Index(name="IDX_tbl_board_reply_board_idx", columnList = "board_idx")}
)
public class BoardReplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '아이디' COLLATE 'utf8mb4_unicode_ci'")
    private String reply_user_id;
    @Column(columnDefinition = "VARCHAR(200) NOT NULL COMMENT '댓글 제목' COLLATE 'utf8mb4_unicode_ci'")
    private String reply_title;
    @Column(columnDefinition = "VARCHAR(2000) NULL DEFAULT NULL COMMENT '댓글 내용' COLLATE 'utf8mb4_unicode_ci'")
    private String reply_content;

    public void modify(String reply_title, String reply_content) {
        this.reply_title = reply_title;
        this.reply_content = reply_content;
        super.setUpdated_at(LocalDateTime.now());
    }
}