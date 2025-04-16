package net.fullstack10.controller;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.dto.BbsDTO;
import net.fullstack10.service.BbsService;

import java.io.IOException;

@WebServlet(name = "/BbsView", value = "/net/fullstack10/view.do")
public class BbsView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = (req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0);

        BbsDTO dto = null;
        try {
            dto = BbsService.INSTANCE.view(idx);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/WEB-INF/views/bbs/view.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}