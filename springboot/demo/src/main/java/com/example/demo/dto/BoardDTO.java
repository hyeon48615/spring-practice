package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
    private Long idx;
    @NotEmpty(message="작성자는 필수입니다.")
    private String user_id;
    @NotEmpty(message="제목은 필수입니다.")
    @Size(min=1, max=100, message="제목은 1자 이상 100자 이하로 작성해주세요.")
    private String title;
    @NotEmpty(message="내용은 필수입니다.")
    @Size(min=1, max=19000, message="내용은 1자 이상 19,000자 이하로 작성해주세요.")
    private String content;
    @NotNull(message="출력날짜는 필수 입니다.")
    private LocalDate display_date;
    private int read_cnt;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
