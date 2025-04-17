package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.dto.BbsDTO;
import net.fullstack10.service.BbsService;
import net.fullstack10.util.JSFunction;

import java.io.IOException;

@WebServlet(name = "/BbsRegistController", value = "/bbs/regist.do")
public class BbsRegistController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String rtnParam = req.getParameter("rtnParam");
        req.setAttribute("rtnParam", rtnParam);

        req.getRequestDispatcher("/WEB-INF/views/bbs/regist.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String user_id = req.getParameter("user_id");

        if (title == null || title.length() < 1 || title.length() > 20) {
            JSFunction.alertBack(res, "제목은 1자 이상 20자 이하로 입력해주세요.");
            return;
        }
        if (user_id == null || user_id.length() < 1 || user_id.length() > 20) {
            JSFunction.alertBack(res, "작성자는 1자 이상 20자 이하로 입력해주세요.");
            return;
        }
        if (content == null || content.length() < 1) {
            JSFunction.alertBack(res, "내용은 1자 이상 입력해주세요.");
            return;
        }

        BbsDTO dto = BbsDTO.builder()
                .title(title)
                .content(content)
                .user_id(user_id)
                .build();

        try {
            service.regist(dto);

            String rtnParam = req.getParameter("rtnParam");
            String redirectURL = (rtnParam != null && !rtnParam.isEmpty() ? "./list.do?" + rtnParam : "./list.do");

            JSFunction.alertLocation(res, "등록에 성공했습니다.", redirectURL);
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(res, "등록에 실패했습니다.");
        }
    }
}