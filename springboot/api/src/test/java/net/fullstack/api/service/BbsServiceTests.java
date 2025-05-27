package net.fullstack.api.service;

import lombok.extern.log4j.Log4j2;
import net.fullstack.api.dto.BbsDTO;
import net.fullstack.api.dto.PageRequestDTO;
import net.fullstack.api.dto.PageResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Log4j2
@SpringBootTest
public class BbsServiceTests {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BbsService bbsService;

    @Test
    public void testGetTotalCount() {
        log.info("=========================================");
//        int totalCount = bbsService.getTotalCount();
//        log.info("BbsServiceTests >> testGetTotalCount >> totalCount : {}", totalCount);
        log.info("=========================================");
    }

    @Test
    public void testBbsList() {
        log.info("========================================");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page_no(1)
                .page_size(10)
                .page_block_size(10)
                .search_category("title")
                .search_word("테스트")
                .linkParams("")
                .build();
        List<BbsDTO> dtoList = bbsService.bbsList(pageRequestDTO);
        dtoList.forEach(dto -> {
            log.info("dto : {}", dto.toString());
        });
        log.info("========================================");
    }

    @Test
    public void testBbsView() {
        log.info("========================================");
//        BbsDTO dto = bbsService.getView(1L);
//        log.info("BbsServiceTests >> testBbsView >> dto: {}", dto);
        log.info("========================================");
    }

    @Test
    public void testBbsRegist() {
        log.info("========================================");
//        BbsDTO dto = BbsDTO.builder()
//                .title("테스트 타이틀")
//                .content("테스트 내용")
//                .user_id("user9")
//                .display_date("2025-05-20")
//                .reg_date(LocalDateTime.now())
//                .build();
//        long rtnResult = bbsService.bbsRegist(dto);
//        log.info("BbsServiceTests >> testBbsRegist >> rtnResult={}", rtnResult);
        log.info("======================================");
    }


    @Test
    public void testBbsModify() {
        log.info("========================================");
//        BbsDTO dto = BbsDTO.builder()
//                .idx(103L)
//                .title("테스트 타이틀 103")
//                .content("테스트 내용 103")
//                .user_id("user9")
//                .display_date("2025-05-20")
//                .modify_date(LocalDateTime.now())
//                .build();
//        long rtnResult = bbsService.bbsModify(dto);
//        log.info("BbsServiceTests >> testBbsModify >> rtnResult={}", rtnResult);
        log.info("======================================");
    }

    @Test
    public void testBbsDel() {
        log.info("========================================");
//        bbsService.bbsDelete(103);
        log.info("======================================");
    }

    @Test
    public void testBoardList() {
        log.info("========================================");
        log.info("BbsServiceTests >> testBoardList START");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        PageRequestDTO reqDTO = PageRequestDTO.builder()
                .page_no(1)
                .page_size(10)
                .page_block_size(10)
                .search_category("title")
                .search_word("테스트")
                .linkParams("")
                .build();
        PageResponseDTO<BbsDTO> resDTO = bbsService.boardList(reqDTO);
        log.info("resDTO = {}", resDTO);
        log.info("BbsServiceTests >> testBoardList END");
        log.info("========================================");
    }

}