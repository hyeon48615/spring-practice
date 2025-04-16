package net.fullstack10.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BbsVO {
    private int idx;                    // 인덱스
    private int bbs_ref_idx;            // 참조글 인덱스
    private int bbs_level;              // 댓글 레벨
    private int sort_order;             // 글 레벨별 정렬 순서
    private String title;               // 글 제목
    private String content;             // 글 내용
    private String user_id;             // 작성자 아이디
    private int read_cnt;               // 조회수
    private LocalDateTime created_at;   // 등록일
    private LocalDateTime updated_at;   // 수정일
}
