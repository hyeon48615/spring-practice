package net.fullstack.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.net.URLEncoder;
import java.util.List;

@Log4j2
@Data
public class PageResponseDTO<E> {
    private long total_count;
    private int page_no;
    private int page_size;
    private int total_page;
    private long page_skip_count;
    private int page_block_size;
    private int page_block_start;
    private int page_block_end;

    private int first_page;
    private int last_page;
    private boolean prev_page_flag;
    private boolean next_page_flag;

    List<E> dtoList;

    private PageRequestDTO reqDTO;

    private String search_category;
    private String search_word;
    private String search_date_from;
    private String search_date_to;
    private String linkParams;

    public PageResponseDTO() {}

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO reqDTO, long totalCount, List<E> dtoList) {
        this.reqDTO = reqDTO;
        this.total_count = totalCount;
        this.page_no = reqDTO.getPage_no();
        this.page_size = reqDTO.getPage_size();
        this.page_skip_count = reqDTO.getPage_skip_count();
        this.total_page = (this.total_count > 0 ?
                                (int)Math.ceil((this.total_count/(double)this.page_size)) : 1);
        this.page_block_size = reqDTO.getPage_block_size();

        this.setPage_block_start();
        this.setPage_block_end();
        this.prev_page_flag = (this.page_block_start > 1);
        this.next_page_flag = (this.total_page > this.page_block_end);

        this.dtoList = dtoList;

        this.search_category = reqDTO.getSearch_category();
        this.search_word = reqDTO.getSearch_word();
        this.search_date_from = reqDTO.getSearch_date_from();
        this.search_date_to = reqDTO.getSearch_date_to();
    }

    public int getTotal_page(){
        return (this.total_count > 0 ? (int)Math.ceil((this.total_count/(double)this.page_size)) : 1);
    }

    public int getPage_skip_count(){
        return (this.page_no-1)*this.page_size;
    }

    public void setPage_block_start(){
        if( this.page_no % this.page_block_size == 0 ){
            this.page_block_start = this.page_no - (this.page_block_size - 1);
        } else {
            this.page_block_start = ((int)Math.floor(this.page_no/(double)this.page_block_size)*this.page_block_size) + 1;
        }
    }

    public void setPage_block_end(){
        this.page_block_end = (int)Math.ceil(this.page_no/(double)this.page_block_size)*this.page_block_size;
        this.page_block_end = Math.min(this.page_block_end, this.total_page);
    }

    public String getLinkParams(){
        StringBuffer sb = new StringBuffer();
        //sb.append("page_no="+this.page_no);
        sb.append("page_size="+this.page_size);

        if ( this.search_category != null ){
            sb.append("&search_category="+this.search_category);
        }
        if ( this.search_word != null ){
            try{
                sb.append("&search_word="+ URLEncoder.encode(this.search_word, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ( this.search_date_from != null ){
            sb.append("&search_date_from="+this.search_date_from);
        }
        if ( this.search_date_to != null ){
            sb.append("&search_date_to="+this.search_date_to);
        }

        return sb.toString();
    }

}