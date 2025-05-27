package net.fullstack.api.repository;

import lombok.extern.log4j.Log4j2;
import net.fullstack.api.domain.BbsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Log4j2
@SpringBootTest
public class BbsRepositoryTests {
    @Autowired
    private BbsRepository bbsRepository;

    @Test
    public void testGetNow() {
        log.info("========================================");
//        String now = bbsRepository.getNow();
//        log.info(now);
        log.info("========================================");
    }

    @Test
    public void testBbsResgist() {
        log.info("========================================");
        IntStream.rangeClosed(1, 100).forEach(i -> {
            String displayDate = LocalDate.of(2025, 5, (i % 10 == 0 ? 10 : i % 10)).toString();
            BbsEntity bbsEntity = BbsEntity.builder()
                    .title("테스트 제목 " + i)
                    .content("테스트 내용 " + i)
                    .user_id("user" + (i%10==0?1:2))
                    .display_date(displayDate)
                    .build();
            BbsEntity result = bbsRepository.save(bbsEntity);
            log.info("testBbsResgist : {}", result.toString());
        });

//        BbsEntity bbsEntity = BbsEntity.builder()
//                .title("테스트 제목 101 ")
//                .content("테스트 내용 101 ")
//                .user_id("user1")
//                .display_date("2025-05-20")
//                .build();
//        BbsEntity result = bbsRepository.save(bbsEntity);
//        log.info(result.toString());
        log.info("========================================");
    }

    @Test
    public void testBbsView() {
        log.info("=======================================");
//        Optional<BbsEntity> result = bbsRepository.findById(1L);
//        BbsEntity bbsEntity = result.orElse(null);
//        log.info("testBbsView : {}", bbsEntity.toString());
        log.info("=======================================");
    }

    @Test
    public void testBbsAll() {
        log.info("=======================================");
//        List<BbsEntity> result = bbsRepository.findAll();
//        result.forEach(System.out::println);
        //log.info("testBbsAll : {}", result.toString());
        log.info("=======================================");
    }

    @Test
    public void testBbsModify() {
        log.info("======================================");
//        Optional<BbsEntity> result = bbsRepository.findById(1);
//        BbsEntity bbsEntity = result.orElse(
//                BbsEntity.builder()
//                        .title("테스트 title 11")
//                        .content("테스트 content 11")
//                        .user_id("user1")
//                        .display_date("2025-05-18")
//                        .build()
//        );
//        BbsEntity bbsEntity = BbsEntity.builder()
//                        .idx((long)1)
//                        .title("테스트 title 11")
//                        .content("테스트 content 11")
//                        .user_id("user1")
//                        .display_date("2025-05-18")
//                        .build();
//        //bbsEntity.modify(1L, "user1", "테스트 title 11", "테스트 content 11", "2025-05-18");
//        BbsEntity resultEntity = bbsRepository.save(bbsEntity);
//        log.info("testBbsModify : {}", bbsEntity.toString());
//        log.info("resultEntity : {}", resultEntity.toString());
        log.info("======================================");
    }

    @Test
    public void testBbsDelete() {
        log.info("======================================");
//        bbsRepository.deleteById(101L);
        log.info("======================================");
    }

    @Test
    public void testBbsList() {
        log.info("======================================");
//        Pageable pageable = PageRequest.of(1, 10, Sort.by("idx").descending());
//        Page<BbsEntity> result = bbsRepository.findAll(pageable);
//        List<BbsEntity> bbsList = result.getContent();
//
//        log.info("bbsList : {}", bbsList.toString());
        log.info("=====================================");
    }

    @Test
    public void testSearch1() {
        log.info("======================================");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        bbsRepository.search1(pageable);
        log.info("=====================================");
    }

    @Test
    public void testSearch2() {
        log.info("=====================================");
        Pageable pageable = PageRequest.of(0, 10, Sort.by("idx").descending());
        String[] categories = {"title", "content"};
        String search_word = "5";
        Page<BbsEntity> result = bbsRepository.search2(pageable, categories, search_word);
        List<BbsEntity> bbsList = result.getContent();
        long total_cnt = result.getTotalElements();

        bbsList.forEach(bbsEntity -> {
           log.info("bbsEntity = " + bbsEntity);
        });

        log.info("testSearch2 >> totla page : {}", result.getTotalPages());
        log.info("testSearch2 >> page number : {}", result.getNumber());
        log.info("testSearch2 >> prev : {}", result.hasPrevious());
        log.info("testSearch2 >> next : {}", result.hasNext());
        log.info("testSearch2 >> total_cnt : {}", total_cnt);
        log.info("======================================");
    }
}