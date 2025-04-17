package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.service.BbsService;
import net.fullstack10.util.CommonUtil;
import net.fullstack10.util.JSFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/BbsDeleteController", value = "/bbs/delete.do")
public class BbsDeleteController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;
    private CommonUtil cUtil = new CommonUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        JSFunction.alertBack(res, "올바르지 않은 접근입니다.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = cUtil.parseInt(req.getParameter("delete_idx"));
        String[] delete_list = req.getParameterValues("delete_list");

        try {
            if (idx > 0) {
                service.delete(idx);
            } else if (delete_list != null && delete_list.length > 0) {
                List<Integer> idxList = new ArrayList<>();
                for (String s : delete_list) {
                    if (cUtil.isNumeric(s)) idxList.add(Integer.parseInt(s));
                }
                service.delete(idxList);
            } else {
                JSFunction.alertBack(res, "삭제할 항목을 선택하세요.");
                return;
            }

            String rtnParam = req.getParameter("rtnParam");
            String redirectURL = (rtnParam != null && !rtnParam.isEmpty() ? "./list.do?" + rtnParam : "./list.do");

            JSFunction.alertLocation(res, "삭제에 성공했습니다.", redirectURL);
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(res, "삭제에 실패했습니다.");
        }
    }
}