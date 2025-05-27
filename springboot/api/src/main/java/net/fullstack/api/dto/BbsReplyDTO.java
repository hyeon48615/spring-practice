package net.fullstack.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BbsEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Log4j2
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BbsReplyDTO {
    private long idx;
    @NotNull
    private long board_idx;
    @NotBlank
    private String reply_user_id;
    @NotBlank
    private String reply_title;
    private String reply_content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updated_at;
}