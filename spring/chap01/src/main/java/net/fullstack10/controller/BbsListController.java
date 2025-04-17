package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.dto.BbsDTO;
import net.fullstack10.service.BbsService;
import net.fullstack10.util.CommonUtil;
import net.fullstack10.util.DateUtil;
import net.fullstack10.util.JSFunction;
import net.fullstack10.util.PageUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "/BbsListController", value = "/bbs/list.do")
public class BbsListController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;

    private CommonUtil cUtil = new CommonUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int total_count = 0;
        int page_no = cUtil.setPageParam(req.getParameter("page_no"), 1);
        int page_size = cUtil.setPageParam(req.getParameter("page_size"), 10);
        int page_block_size = cUtil.setPageParam(req.getParameter("page_block_size"), 10);
        int page_skip_count = (page_no -1) * page_size;

        String search_date1 = cUtil.setDateParam(req.getParameter("search_date1"), "yyyy-MM-dd");
        String search_date2 = cUtil.setDateParam(req.getParameter("search_date2"), "yyyy-MM-dd");

        if (!search_date1.isEmpty() && !search_date2.isEmpty()) {
            LocalDate start = DateUtil.toLocalDate(search_date1);
            LocalDate end = DateUtil.toLocalDate(search_date2);

            if (start != null && end != null && start.isBefore(end)) {
                JSFunction.alertBack(res, "시작일이 종료일보다 클 수 없습니다.");
                return;
            }
        }

        String search_category = cUtil.setStringParam(req.getParameter("search_category"));
        String search_word = cUtil.setStringParam(req.getParameter("search_word"));

        // String order_column = cUtil.setStringParam(req.getParameter("order_column"));
        // String order_direction = cUtil.setStringParam(req.getParameter("order_direction"));

        String queryString = "page_size=" + page_size + "&page_block_size=" + page_block_size;
        queryString += (!search_date1.isEmpty() && !search_date2.isEmpty() ? "&search_date1=" + search_date1 + "&search_date2=" + search_date2 : "");
        queryString += (!search_category.isEmpty() && !search_word.isEmpty() ? "&search_category=" + search_category + "&search_word=" + search_word : "");
        // queryString += (!order_column.isEmpty() && !order_direction.isEmpty() ? "&order_column=" + order_column + "&order_direction=" + order_direction : "");

        Map<String, String> map = new HashMap<>();
        map.put("search_date1", search_date1);
        map.put("search_date2", search_date2);
        map.put("search_category", search_category);
        map.put("search_word", search_word);
        map.put("page_skip_count", String.valueOf(page_skip_count));
        map.put("page_size", String.valueOf(page_size));

        List<BbsDTO> bbsList = null;
        try {
            bbsList = service.list(map);
            total_count = service.totalCount(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("map", map);
        req.setAttribute("rtnParam", URLEncoder.encode(queryString + "&page_no=" + page_no, "UTF-8"));
        req.setAttribute("paging", PageUtil.printPagingArea(total_count, page_no, page_size, page_block_size, "list.do?" + queryString));
        req.setAttribute("bbsList", bbsList);

        req.getRequestDispatcher("/WEB-INF/views/bbs/list.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}