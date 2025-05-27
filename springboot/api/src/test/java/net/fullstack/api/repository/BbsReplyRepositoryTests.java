package net.fullstack.api.repository;

import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BbsReplyEntity;
import net.fullstack.api.repository.bbs.BbsReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Log4j2
@SpringBootTest
public class BbsReplyRepositoryTests {
    @Autowired
    private BbsReplyRepository bbsReplyRepository;

    @Test
    public void testBbsReplyRegist(){
//        BbsEntity board = BbsEntity.builder().idx(1L).build();
//        IntStream.rangeClosed(1, 10).forEach(
//                i -> {
//                    BbsReplyEntity bbsReplyEntity = BbsReplyEntity.builder()
//                            .board(board)
//                            .reply_user_id("user"+ i)
//                            .reply_title("댓글 테스트 제목 "+ i)
//                            .reply_content("댓글 테스트 내용 "+ i)
//                            .reply_date(LocalDateTime.now())
//                            .reg_date(LocalDateTime.now())
//                            .build();
//                    BbsReplyEntity result = bbsReplyRepository.save(bbsReplyEntity);
//                    log.info("result : {}", result);
//                }
//        );
    }

    @Test
    public void testBbsReplyList(){
        log.info("===========================================");
        log.info("BbsReplyServiceImplTests >> testBbsReplyList START");
        long bbs_idx = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        Page<BbsReplyEntity> result = bbsReplyRepository.list(bbs_idx, pageable);
        log.info(result);
        log.info("BbsReplyServiceImplTests >> testBbsReplyList END");
        log.info("============================================");
    }
}