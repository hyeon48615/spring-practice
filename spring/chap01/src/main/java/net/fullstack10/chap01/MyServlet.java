package net.fullstack10.chap01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", urlPatterns = "/my")
public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        res.setContentType("text/html");
//        res.setCharacterEncoding("UTF-8");
//
//        PrintWriter out = res.getWriter();
//        out.println("<html><head><title>My Servlet</title></head><body>");
//        out.println("<h1>My Servlet</h1>");
//        out.println("</body></html>");

        // GET 방식 --> 주로 URL 처리
        // 주로 select 작업
        System.out.println("MyServlet");
        req.getRequestDispatcher("/WEB-INF/views/myservlet.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);

        // GET 에서 작성한 페이지에서 넘긴 파라미터에 대한 처리
        // 데이터 등록, 수정, 삭제
    }
}
