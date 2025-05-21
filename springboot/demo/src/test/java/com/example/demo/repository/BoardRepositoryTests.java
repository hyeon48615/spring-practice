package com.example.demo.repository;

import com.example.demo.domain.BoardEntity;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testSetNow() {
        log.info("==================================================");
        String now = boardRepository.getNow();
        log.info("now: {}", now);
        log.info("==================================================");
    }

    @Test
    public void testBbsRegist() {
        log.info("==================================================");
        IntStream.rangeClosed(1, 100).forEach(i -> {
            BoardEntity boardEntity = BoardEntity.builder()
                    .title("테스트 제목 " + i)
                    .content("테스트 내용 " + i)
                    .user_id("user" + (i%10==0?1:2))
                    .display_date(LocalDate.now())
                    .build();

            BoardEntity result = boardRepository.save(boardEntity);
            log.info("result_{}: {}", i, result.toString());
        });
        log.info("==================================================");
    }

    @Test
    public void testBbsView() {
        log.info("==================================================");
        Optional<BoardEntity> result = boardRepository.findById(1L);
        BoardEntity boardEntity = result.orElse(null);
        log.info("bbsEntity: {}", boardEntity.toString());
        log.info("==================================================");
    }

    @Test
    public void testBbsAll() {
        log.info("==================================================");
        List<BoardEntity> result = boardRepository.findAll();
        result.forEach(System.out::println);
        // log.info("result: {}", result.toString());
        log.info("==================================================");
    }

    @Test
    public void testBbsModify() {
        log.info("==================================================");
        Optional<BoardEntity> result = boardRepository.findById(1L);
        BoardEntity boardEntity = result.orElse(null);
        // bbsEntity.update(1L, "user1", "테스트 제목 1 수정", "테스트 내용 1 수정", "2025-05-20");
        BoardEntity resultEntity = boardRepository.save(boardEntity);
        log.info("resultEntity: {}", resultEntity.toString());
        log.info("==================================================");
    }

    @Test
    public void testBbsDelete() {
        log.info("==================================================");
        boardRepository.deleteById(1L);
        log.info("==================================================");
    }

    @Test
    public void testSearch() {
        log.info("==================================================");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        boardRepository.search(pageable);
        log.info("==================================================");
    }

    @Test
    public void testSearch2() {
        log.info("==================================================");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        String[] categories = {"title", "content"};
        String search_word = "1";
        Page<BoardEntity> result = boardRepository.search2(pageable, categories, search_word);

        List<BoardEntity> content = result.getContent();
        Long totalCount = result.getTotalElements();

        log.info("content: {}", content);
        log.info("totalCount: {}", totalCount);
        log.info("==================================================");
    }
}
