package net.fullstack10.bbs.controller;

import net.fullstack10.bbs.dto.BbsDTO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.bbs.service.BbsService;

import java.io.IOException;

@WebServlet(name = "/BbsView", value = "/net/fullstack10/view.do")
public class BbsView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int idx = (req.getParameter("idx") != null ? Integer.parseInt(req.getParameter("idx")) : 0);

        BbsDTO dto = BbsService.INSTANCE.view(idx);


        req.getRequestDispatcher("/WEB-INF/views/bbs/view.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}