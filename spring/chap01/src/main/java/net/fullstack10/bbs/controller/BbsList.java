package net.fullstack10.bbs.controller;

import net.fullstack10.bbs.dto.BbsDTO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import net.fullstack10.bbs.service.BbsService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "/BbsList", value = "/net/fullstack10/list.do")
public class BbsList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("====================");
        System.out.println("List >> doGet");
        System.out.println("====================");

        List<BbsDTO> bbsList = BbsService.INSTANCE.list();

        req.setAttribute("bbsList", bbsList);
        req.getRequestDispatcher("/WEB-INF/views/bbs/list.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}