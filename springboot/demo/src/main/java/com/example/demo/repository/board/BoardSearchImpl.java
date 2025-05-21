package com.example.demo.repository.board;

import com.example.demo.domain.BoardEntity;
import com.example.demo.domain.QBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> search(Pageable pageable) {
        // 1. Q도메인 객체 생성
        QBoardEntity boardQ = QBoardEntity.boardEntity;

        // 2. Q도메인 이용한 기본 메인 쿼리 작성
        JPQLQuery<BoardEntity> query = from(boardQ);

        // 3. 조건절, orderby, limit, groupby
        query.where(boardQ.title.containsIgnoreCase("title"));
        query.orderBy(boardQ.idx.desc());
        // SELECT * FROM tbl_board
        // WHERE title LIKE '%title%'
        // ORDER BY idx DESC

        // 4. 실행
        List<BoardEntity> list = query.fetch();
        long total_cnt = query.fetchCount();

        return null;
    }

    @Override
    public Page<BoardEntity> search2(Pageable pageable, String[] categories, String search_word) {
        QBoardEntity boardQ = QBoardEntity.boardEntity;
        JPQLQuery<BoardEntity> query = from(boardQ);

        if ((categories != null && categories.length > 0) && (search_word != null && !search_word.isBlank())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String category : categories) {
                if (category.equals("title")) {
                    // query.where(boardQ.title.containsIgnoreCase(search_word));
                    booleanBuilder.or(boardQ.title.containsIgnoreCase(search_word));
                }
                else if (category.equals("content")) {
                    // query.where(boardQ.content.containsIgnoreCase(search_word));
                    booleanBuilder.or(boardQ.content.containsIgnoreCase(search_word));
                }
                else if (category.equals("user_id")) {
                    // query.where(boardQ.user_id.containsIgnoreCase(search_word));
                    booleanBuilder.or(boardQ.user_id.containsIgnoreCase(search_word));
                }
            }
            query.where(booleanBuilder);
        }
        query.where(boardQ.idx.gt(0));
        query.orderBy(boardQ.idx.desc());

        if (this.getQuerydsl() != null) {
            this.getQuerydsl().applyPagination(pageable, query);
        }

        List<BoardEntity> list = query.fetch();
        long total_cnt = query.fetchCount();

        return new PageImpl<>(list, pageable, total_cnt);
    }
}
