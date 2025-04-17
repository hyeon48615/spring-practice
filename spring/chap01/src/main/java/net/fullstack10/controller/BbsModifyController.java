package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.dto.BbsDTO;
import net.fullstack10.service.BbsService;
import net.fullstack10.util.CommonUtil;
import net.fullstack10.util.JSFunction;

import java.io.IOException;

@WebServlet(name = "/BbsModifyController", value = "/bbs/modify.do")
public class BbsModifyController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;
    private CommonUtil cUtil = new CommonUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = cUtil.parseInt(req.getParameter("idx"));

        BbsDTO dto = null;
        try {
            dto = service.view(idx);
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(res, "해당 게시글 정보를 찾을 수 없습니다.");
            return;
        }

        String rtnParam = req.getParameter("rtnParam");
        req.setAttribute("rtnParam", rtnParam);

        req.setAttribute("bbs", dto);
        req.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = cUtil.parseInt(req.getParameter("idx"));
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
                .idx(idx)
                .title(title)
                .content(content)
                .user_id(user_id)
                .build();

        try {
            service.modify(dto);

            String rtnParam = req.getParameter("rtnParam");
            String redirectURL = (rtnParam != null && !rtnParam.isEmpty() ? "./view.do?idx=" + idx + "&rtnParam=" + rtnParam : "./view.do?idx=" + idx);

            JSFunction.alertLocation(res, "수정에 성공했습니다.", redirectURL);
        } catch (Exception e) {
            e.printStackTrace();
            JSFunction.alertBack(res, "수정에 실패했습니다.");
        }
    }
}