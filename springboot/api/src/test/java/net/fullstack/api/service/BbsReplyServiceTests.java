package net.fullstack.api.service;

import lombok.extern.log4j.Log4j2;
import net.fullstack.api.dto.BoardReplyDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@SpringBootTest
public class BbsReplyServiceTests {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardReplyService replyService;

    @Test
    public void testBbsReplyRegist(){
        log.info("===========================================");
        log.info("BbsReplyServiceTests >> testBbsReplyRegist START");
//        BbsReplyDTO bbsReplyDTO = BbsReplyDTO.builder()
//            .board_idx(1L)
//            .reply_user_id("user1")
//            .reply_title("댓글 테스트 제목 1")
//            .reply_content("댓글 테스트 내용 1")
//            .reply_date(LocalDateTime.now())
//            .build();
//        BbsReplyEntity bbsReplyEntity = modelMapper.map(bbsReplyDTO, BbsReplyEntity.class);
//        bbsReplyEntity.setReg_date(LocalDateTime.now());

//        BbsEntity board = BbsEntity.builder().idx(1L).build();
//        BbsReplyEntity bbsReplyEntity = BbsReplyEntity.builder()
//            .board(board)
//            .reply_user_id("user1")
//            .reply_title("서비스 댓글 테스트 제목 1")
//            .reply_content("서비스 댓글 테스트 내용 1")
//            .reply_date(LocalDateTime.now())
//            .reg_date(LocalDateTime.now())
//            .build();
//        BbsReplyEntity result = bbsReplyRepository.save(bbsReplyEntity);
//        log.info("BbsReplyServiceTests >> testBbsReplyRegist >> result : {}", result);
        log.info("BbsReplyServiceTests >> testBbsReplyRegist END");
        log.info("===========================================");
    }

    @Test
    public void testBbsReplyList() {
        log.info("===========================================");
        log.info("BbsReplyServiceImplTests >> testBbsReplyList START");

        long bbs_idx = 1L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        List<BoardReplyDTO> replyDTOList = replyService.bbsReplyList(bbs_idx, pageable);
        log.info("BbsReplyServiceImplTests >> testBbsReplyList >> replyDTOList : {}", replyDTOList);
        log.info("BbsReplyServiceImplTests >> testBbsReplyList END");
        log.info("============================================");
    }

    @Test
    public void testBbsReplyView() {
        log.info("===========================================");
        log.info("BbsReplyServiceImplTests >> testBbsReplyView START");
        long idx = 1L;
        BoardReplyDTO replyDTO = replyService.bbsReplyView(idx);
        log.info(replyDTO);
        log.info("BbsReplyServiceImplTests >> testBbsReplyView END");
        log.info("============================================");
    }

    @Test
    public void testBbsReplyModify() {
        log.info("============================================");
        log.info("BbsReplyServiceImplTests >> testBbsReplyModify START");
        long idx = 13L;
        BoardReplyDTO replyDTO = BoardReplyDTO.builder()
                .idx(idx)
                .board_idx(10)
                .reply_title("서비스 게시판 제목 수정")
                .reply_content("서비스 게시판 내용 수정")
                .updated_at(LocalDateTime.now())
                .build();
        log.info("BbsReplyServiceImplTests >> testBbsReplyModify  >> replyDTO : {}", replyDTO);

        replyService.bbsReplyModify(replyDTO);

        log.info("BbsReplyServiceImplTests >> testBbsReplyModify END");
        log.info("============================================");
    }


    @Test
    public void testBbsReplyDelete() {
        log.info("============================================");
        log.info("BbsReplyServiceImplTests >> testBbsReplyDelete START");
        long idx = 16L;
        replyService.bbsReplyDelete(idx);
        log.info("BbsReplyServiceImplTests >> testBbsReplyDelete END");
        log.info("============================================");
    }
}