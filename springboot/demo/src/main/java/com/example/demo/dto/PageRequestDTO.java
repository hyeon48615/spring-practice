package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;

    private String search_category;
    private String search_word;
    private LocalDate start_date;
    private LocalDate end_date;

    private String link;

    public Pageable getPageable(String ... props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    public String getLink() {
        if (link == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("page=" + this.page);
            sb.append("&size=" + this.size);

            if (search_category != null && search_word != null) {
                sb.append("&search_category=" + search_category);
                sb.append("&search_word=" + URLEncoder.encode(search_word, StandardCharsets.UTF_8));
            }

            if (start_date != null && end_date != null) {
                sb.append("&start_date=" + start_date);
                sb.append("&end_date=" + end_date);
            }

            link = sb.toString();
        }
        return link;
    }
}
