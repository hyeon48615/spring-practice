package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegist() {
        log.info("==================================================");
        BoardDTO bbsDTO = BoardDTO.builder()
                .title("테스트 제목 1")
                .content("테스트 내용 1")
                .user_id("user1")
                .display_date(LocalDate.now())
                .build();

        Long idx = boardService.regist(bbsDTO);
        log.info("idx: {}", idx);
        log.info("==================================================");
    }

    @Test
    public void testModify() {
        log.info("==================================================");
        BoardDTO bbsDTO = BoardDTO.builder()
                .idx(1L)
                .title("테스트 제목 1 수정")
                .content("테스트 내용 1 수정")
                .display_date(LocalDate.now())
                .build();

        boardService.modify(bbsDTO);
        log.info("==================================================");
    }

    @Test
    public void testList() {
        log.info("==================================================");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info("responseDTO: {}", responseDTO.toString());
        log.info("==================================================");
    }

    @Test
    public void testSearch() {
        log.info("==================================================");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .search_category("title")
                .search_word("1")
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.search(pageRequestDTO);
        log.info("responseDTO: {}", responseDTO.toString());
        log.info("==================================================");
    }
}
