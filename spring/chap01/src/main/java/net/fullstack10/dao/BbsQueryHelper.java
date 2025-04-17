package net.fullstack10.dao;

import java.util.List;

public class BbsQueryHelper {
    private BbsQueryHelper() {}

    // 등록일
    public static void addDateFilter(StringBuilder sql, List<String> params, String date1, String date2) {
        if (date1!= null && date2 != null && !date1.isEmpty() && !date2.isEmpty()) {
            sql.append(" and created_at between ? and ? ");
            params.add(date1);
            params.add(date2);
        }
    }

    // 검색 카테고리
    public static void addCategoryFilter(StringBuilder sql, List<String> params, String category, String word) {
        if (category != null && word != null && !category.isEmpty() && !word.isEmpty()) {
            switch (category) {
                case "title":
                    sql.append(" and title like ? ");
                    params.add("%" + word + "%");
                    break;
                case "content":
                    sql.append(" and content like ? ");
                    params.add("%" + word + "%");
                    break;
                case "user_id":
                    sql.append(" and user_id like ? ");
                    params.add("%" + word + "%");
                    break;
            }
        }
    }

    // 정렬 조건
    public static void addOrderByFilter(StringBuilder sql, String column, String direction) {
        List<String> allowedColumns = List.of("created_at");
        List<String> allowedDirections = List.of("asc", "desc");

        if (column != null && direction != null && allowedColumns.contains(column) && allowedDirections.contains(direction)) {
            sql.append(" order by " + column + " " + direction + " , idx desc ");
        } else {
            sql.append(" order by created_at desc ");
        }
    }

    // 페이징
    public static void addPageFilter(StringBuilder sql, String pageSkipCount, String pageSize) {
        if (pageSkipCount != null && pageSize != null && !pageSkipCount.isEmpty() && !pageSize.isEmpty()) {
            sql.append(" LIMIT " + pageSkipCount + " , " + pageSize + " ");
        }
    }
}
