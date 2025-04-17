package net.fullstack10.dao;

import lombok.Cleanup;
import net.fullstack10.domain.BbsVO;
import net.fullstack10.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BbsDAO {

    public int getTotalCount(Map<String, String> map) throws Exception {
        int result = 0;
        List<String> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append( "select count(*) from tbl_bbs ");
        sql.append(" where 1=1 ");

        BbsQueryHelper.addDateFilter(sql, params, map.get("search_date1"), map.get("search_date2"));
        BbsQueryHelper.addCategoryFilter(sql, params, map.get("search_category"), map.get("search_word"));
        BbsQueryHelper.addOrderByFilter(sql, map.get("order_column"), map.get("order_direction"));
        BbsQueryHelper.addPageFilter(sql, map.get("page_skip_count"), map.get("page_size"));

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        int index = 1;
        for (String param : params) {
            ps.setString(index++, param);
        }

        @Cleanup ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return result;
    }

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

    public List<BbsVO> selectAll(Map<String, String> map) throws Exception {
        List<String> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" idx, title, content, user_id, read_cnt, created_at, updated_at ");
        sql.append(" from tbl_bbs ");
        sql.append(" where 1=1 ");

        BbsQueryHelper.addDateFilter(sql, params, map.get("search_date1"), map.get("search_date2"));
        BbsQueryHelper.addCategoryFilter(sql, params, map.get("search_category"), map.get("search_word"));
        BbsQueryHelper.addOrderByFilter(sql, map.get("order_column"), map.get("order_direction"));
        BbsQueryHelper.addPageFilter(sql, map.get("page_skip_count"), map.get("page_size"));

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        int index = 1;
        for (String param : params) {
            ps.setString(index++, param);
        }

        @Cleanup ResultSet rs = ps.executeQuery();

        List<BbsVO> list = new ArrayList<BbsVO>();
        while (rs.next()) {
            BbsVO vo = BbsVO.builder()
                    .idx(rs.getInt("idx"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .user_id(rs.getString("user_id"))
                    .read_cnt(rs.getInt("read_cnt"))
                    .created_at(DateUtil.toLocalDateTime(rs.getTimestamp("created_at")))
                    .updated_at(DateUtil.toLocalDateTime(rs.getTimestamp("updated_at")))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public BbsVO selectByidx(int idx) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" idx, title, content, user_id, read_cnt, created_at, updated_at ");
        sql.append(" from tbl_bbs ");
        sql.append(" where idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        ps.setInt(1, idx);

        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return BbsVO.builder()
                    .idx(rs.getInt("idx"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .user_id(rs.getString("user_id"))
                    .read_cnt(rs.getInt("read_cnt"))
                    .created_at(DateUtil.toLocalDateTime(rs.getTimestamp("created_at")))
                    .updated_at(DateUtil.toLocalDateTime(rs.getTimestamp("updated_at")))
                    .build();
        }

        return null;
    }

    public void update(BbsVO vo) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update tbl_bbs ");
        sql.append(" set title = ?, content = ?, updated_at = now() ");
        sql.append(" where idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        ps.setString(1, vo.getTitle());
        ps.setString(2, vo.getContent());
        ps.setInt(3, vo.getIdx());

        ps.executeUpdate();
    }

    public void delete(int idx) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from tbl_bbs ");
        sql.append(" where idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());
        ps.setInt(1, idx);
        ps.executeUpdate();
    }

    public void delete(List<Integer> idxList) throws Exception {
        if (idxList == null || idxList.isEmpty()) return;

        String placeholders = idxList.stream()
                .map(i -> "?")
                .collect(Collectors.joining(", "));

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from tbl_bbs ");
        sql.append(" where idx in ( ");
        sql.append(placeholders);
        sql.append(" ) ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql.toString());

        int i = 1;
        for (int idx : idxList) {
            ps.setInt(i++, idx);
        }

        ps.executeUpdate();
    }
}
