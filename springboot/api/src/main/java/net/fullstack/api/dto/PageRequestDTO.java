package net.fullstack.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;

@Log4j2
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    @Positive
    @Min(1)
    private int page_no=1;
    @Builder.Default
    @Min(1)
    private int page_size=10;
    @Builder.Default
    @Min(0)
    private long pate_skip_count=0;
    @Builder.Default
    @Min(1)
    private int page_block_size=10;

    private String[] search_categories;
    private String search_category;
    private String search_word;
    private String search_date_from;
    private String search_date_to;
    private String linkParams;

    public long getPage_skip_count() {
        return (this.page_no-1)*this.page_size;
    }

    public String[] getSearch_categories() {
        if (this.search_category == null || this.search_category.isEmpty()) {
            return null;
        }
        return this.search_category.split(",");
    }

    public PageRequest getPageable(String...props) {
        return PageRequest.of(this.page_no-1, this.page_size, Sort.by(props).descending());
    }

    public String getLinkParams() {
        StringBuilder sb = new StringBuilder();
        sb.append("page_size="+this.page_size);
        if(search_category!=null) sb.append("&search_category="+search_category);
        if(search_word!=null) {
            try {
                sb.append("&search_word=" + URLEncoder.encode(search_word, "UTF-8"));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        if(search_date_from!=null) sb.append("&search_date_from="+search_date_from);
        if(search_date_to!=null) sb.append("&search_date_to="+search_date_to);

        return sb.toString();
    }
}