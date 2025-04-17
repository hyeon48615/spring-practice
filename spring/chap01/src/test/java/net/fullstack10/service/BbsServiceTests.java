package net.fullstack10.service;

import lombok.extern.log4j.Log4j2;
import net.fullstack10.dto.BbsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

@Log4j2
public class BbsServiceTests {
    private BbsService service;

    @BeforeEach
    public void ready() {
        service = BbsService.INSTANCE;
    }

    @Test
    public void testRegist() throws Exception {
        BbsDTO dto = BbsDTO.builder()
                .title("service regist test")
                .content("content")
                .user_id("user1")
                .build();

        service.regist(dto);
    }

    @Test
    public void testList() throws Exception {
        log.info(service.list(new HashMap<>()));
    }

    @Test
    public void testView() throws Exception {
        log.info(service.view(1));
    }

    @Test
    public void testModify() throws Exception {
        BbsDTO dto = BbsDTO.builder()
                .idx(1)
                .title("service modify test")
                .content("content")
                .build();

        service.modify(dto);

        log.info(service.view(1));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1);

        log.info(service.list(new HashMap<>()));
    }
}
