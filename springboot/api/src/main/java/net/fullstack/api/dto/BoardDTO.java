package net.fullstack.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDTO {
    private Long idx;
    @NotBlank(message = "아이디를 입력해주세요.")
    private String user_id;
    @Size(min = 1, max = 200, message = "게시글 제목은 1자 이상 200자 이하로 작성해주세요.")
    private String title;
    @Size(min = 1, message = "게시글 내용은 1자 이상 작성해주세요.")
    private String content;
    private String display_date;
    @Builder.Default
    @Min(value = 0, message = "게시글 조회수를 0 이상이어야 합니다.")
    private int read_cnt = 0;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updated_at;

    private long bbs_reply_cnt;
}