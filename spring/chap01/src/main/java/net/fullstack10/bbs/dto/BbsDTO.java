package net.fullstack10.bbs.dto;

import java.time.LocalDateTime;

public class BbsDTO {
    private int idx;
    private String user_id;
    private String title;
    private String content;
    private LocalDateTime reg_date;

    private String search_category;
    private String search_value;
    private String search_date1;
    private String search_date2;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReg_date() {
        return reg_date;
    }

    public void setReg_date(LocalDateTime reg_date) {
        this.reg_date = reg_date;
    }

    public String getSearch_category() {
        return search_category;
    }

    public void setSearch_category(String search_category) {
        this.search_category = search_category;
    }

    public String getSearch_value() {
        return search_value;
    }

    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    public String getSearch_date1() {
        return search_date1;
    }

    public void setSearch_date1(String search_date1) {
        this.search_date1 = search_date1;
    }

    public String getSearch_date2() {
        return search_date2;
    }

    public void setSearch_date2(String search_date2) {
        this.search_date2 = search_date2;
    }

    @Override
    public String toString() {
        return "idx=" + idx + ", user_id=" + user_id + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
                + ", search_category=" + search_category + ", search_value=" + search_value + ", search_date1=" + search_date1 + ", search_date2=" + search_date2;
    }
}
