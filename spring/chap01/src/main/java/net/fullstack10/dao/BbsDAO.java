package net.fullstack10.dao;

import lombok.Cleanup;
import net.fullstack10.domain.BbsVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BbsDAO {
    public void insert(BbsVO vo) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into tbl_bbs ");
        sql.append(" ( user_id, title, content ) ");
        sql.append(" values ");
        sql.append(" ( ?, ?, ? ) ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        ps.setString(1, vo.getUser_id());
        ps.setString(2, vo.getTitle());
        ps.setString(3, vo.getContent());

        ps.executeUpdate();
    }

    public List<BbsVO> selectAll() throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" idx, bbs_ref_idx, bbs_level, sort_order, title, content, user_id, read_cnt, reg_date ");
        sql.append(" from tbl_bbs ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());
        @Cleanup ResultSet rs = ps.executeQuery();

        List<BbsVO> list = new ArrayList<BbsVO>();
        while (rs.next()) {
            BbsVO vo = BbsVO.builder()
                    .idx(rs.getInt("idx"))
                    .bbs_ref_idx(rs.getInt("bbs_ref_idx"))
                    .bbs_level(rs.getInt("bbs_level"))
                    .sort_order(rs.getInt("sort_order"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .user_id(rs.getString("user_id"))
                    .read_cnt(rs.getInt("read_cnt"))
                    .reg_date(rs.getTimestamp("reg_date").toLocalDateTime())
                    .build();

            list.add(vo);
        }

        return list;
    }
}
