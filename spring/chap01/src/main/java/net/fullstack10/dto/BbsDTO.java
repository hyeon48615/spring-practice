package net.fullstack10.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor 포함
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsDTO {
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

/*
    Lombok 어노테이션
    1. EqualsAndHashCode
        - equals() : 두 객체가 같은지 비교
        - hashCode() : 컬렉션에서 객체를 식별할 때 사용
    2. RequiredArgsConstructor
        final 필드나 @NonNull 필드를 파라미터로 갖는 생성자를 자동 생성
 */
