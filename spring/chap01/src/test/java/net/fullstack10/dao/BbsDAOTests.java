package net.fullstack10.dao;

import net.fullstack10.domain.BbsVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class BbsDAOTests {
    private BbsDAO dao;

    @BeforeEach
    public void ready() {
        dao = new BbsDAO();
    }

    @Test
    public void testInsert() throws Exception {
        BbsVO vo = BbsVO.builder()
                .user_id("user1")
                .title("title1")
                .content("content1")
                .build();

        dao.insert(vo);
    }

    @Test
    public void testSelectAll() throws Exception {
        List<BbsVO> list = dao.selectAll(new HashMap<>());

        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testSelectByIdx() throws Exception {
        BbsVO vo = dao.selectByidx(1);

        System.out.println(vo);
    }

    @Test
    public void testUpdate() throws Exception {
        BbsVO vo = BbsVO.builder()
                .idx(1)
                .title("title1 수정")
                .content("content1")
                .build();

        dao.update(vo);

        System.out.println(dao.selectByidx(1));
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(1);

        System.out.println(dao.selectAll(new HashMap<>()));
    }
}
