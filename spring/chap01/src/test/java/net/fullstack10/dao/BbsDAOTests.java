package net.fullstack10.dao;

import net.fullstack10.domain.BbsVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BbsDAOTests {
    private BbsDAO BbsDAO;

    @BeforeEach
    public void ready() {
        BbsDAO = new BbsDAO();
    }

    @Test
    public void testInsert() throws Exception {
        BbsVO vo = BbsVO.builder()
                .user_id("user1")
                .title("title1")
                .content("content1")
                .build();

        BbsDAO.insert(vo);
    }

    @Test
    public void testSelectAll() throws Exception {
        List<BbsVO> list = BbsDAO.selectAll();

        list.forEach(vo -> System.out.println(vo));
    }
}
