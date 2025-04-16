package net.fullstack10.service;

import net.fullstack10.dao.BbsDAO;
import net.fullstack10.dto.BbsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        System.out.println(service.list());
    }

    @Test
    public void testView() throws Exception {
        System.out.println(service.view(1));
    }

    @Test
    public void testModify() throws Exception {
        BbsDTO dto = BbsDTO.builder()
                .idx(1)
                .title("service modify test")
                .content("content")
                .build();

        service.modify(dto);

        System.out.println(service.view(1));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(1);

        System.out.println(service.list());
    }
}
