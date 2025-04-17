package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.dto.BbsDTO;
import net.fullstack10.service.BbsService;
import net.fullstack10.util.CommonUtil;
import net.fullstack10.util.JSFunction;

import java.io.IOException;

@WebServlet(name = "/BbsViewController", value = "/bbs/view.do")
public class BbsViewController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;
    private CommonUtil cUtil = new CommonUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = cUtil.parseInt(req.getParameter("idx"));

        BbsDTO dto = null;
        try {
            dto = service.view(idx);
            dto.setContent(dto.getContent().replaceAll("\r\n|\n", "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(res, "해당 게시글 정보를 찾을 수 없습니다.");
            return;
        }

        String rtnParam = req.getParameter("rtnParam");
        req.setAttribute("rtnParam", rtnParam);

        req.setAttribute("bbs", dto);
        req.getRequestDispatcher("/WEB-INF/views/bbs/view.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}