package net.fullstack.api.repository.bbs;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import net.fullstack.api.domain.BbsEntity;
import net.fullstack.api.domain.QBbsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BbsSearchImpl extends QuerydslRepositorySupport implements BbsSearch {

    public BbsSearchImpl() {
        super(BbsEntity.class);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<BbsEntity> search1(Pageable pageable) {
        //1.Q도메인 객체 생성
        //2.Q도메인을 이용한 기본 메인 쿼리 작성
        //3.조건절, orderby, limit, groupby
        //4.실행

        QBbsEntity bbsQ = QBbsEntity.bbsEntity;    //1.Q도메인객체 생성
        JPQLQuery<BbsEntity> query = from(bbsQ);   //2.Q도메인을 이용한 기본 메인쿼리 작성
                                                        // SELECT .... FROM tbl_board
        query.where(bbsQ.title.containsIgnoreCase("title"));    //3. 조건절, orderby
        query.orderBy(bbsQ.idx.desc());
        // SELECT * FROM tbl_board
        // WHERE title LIKE '%title%'
        // ORDER BY idx DESC

        //4. 실행
        List<BbsEntity> list = query.fetch();
        long total_cnt = query.fetchCount();

        return null;
    }

    /**
     * @param pageable
     * @param categories
     * @param search_word
     * @return
     */
    @Override
    public Page<BbsEntity> search2(Pageable pageable, String[] categories, String search_word) {
        //1. Q도메인 객체 생성
        //2. Q도메인 객체 이용하여 기본 쿼리 작성
        //3. 조건절,,, 생성
        //4. 실행
        QBbsEntity bbsQ = QBbsEntity.bbsEntity;
        JPQLQuery<BbsEntity> query = from(bbsQ);        //SELECT...FROM

        if ( (categories != null && categories.length > 0) && (search_word != null && !search_word.isEmpty()) ) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String category : categories) {
                if ( category.equals("title")) {
                    //query.where(bbsQ.title.containsIgnoreCase(search_word));
                    booleanBuilder.or(bbsQ.title.containsIgnoreCase(search_word));
                }
                else if ( category.equals("content")) {
                    booleanBuilder.or(bbsQ.content.containsIgnoreCase(search_word));
                }
                else if ( category.equals("user_id")) {
                    booleanBuilder.or(bbsQ.user_id.containsIgnoreCase(search_word));
                }
            }
            query.where(booleanBuilder);
        }
        query.where(bbsQ.idx.gt(0));
        query.orderBy(bbsQ.idx.desc());

        if (this.getQuerydsl() != null) {
            this.getQuerydsl().applyPagination(pageable, query);
        }

        List<BbsEntity> list = query.fetch();
        long total_cnt = query.fetchCount();

        PageImpl<BbsEntity> result = new PageImpl<>(list, pageable, total_cnt);
        return result;
    }
}