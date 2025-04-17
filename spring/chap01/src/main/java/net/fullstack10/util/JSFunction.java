package net.fullstack10.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class JSFunction {
    // 메시지 알림창을 띄운 후 이전 페이지로 이동
    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            String script = "<script>";
            if ( msg != "" ) {
                script += "alert('" + msg + "');";
            }
            script += "history.back();";
            script += "</script>";

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(script);
            writer.close();
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 명시한 URL로 이동
    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
            String script = "<script>";
            if ( msg != "" ) {
                script += "alert('" + msg + "');";
            }
            if ( url != "" ) {
                script += "window.location.href='" + url + "'";
            }
            script += "</script>";

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(script);
            writer.close();
        }
        catch (Exception e) {}
    }

    // 메시지 알림창을 띄운 후 명시한 URL로 이동합니다.
    public static void alertLocation(HttpServletResponse resp, String LocationType, String msg, String url) {
        try {
            // 삽입할 자바스크립트 코드
            String script = "<script>";
            if ( msg != "" ) {
                script += "alert('" + msg + "');";
            }
            if ( url != "" ) {
                if ( LocationType.equalsIgnoreCase("href") ) {
                    script += "window.location.href='" + url + "';";
                }
                else if ( LocationType.equalsIgnoreCase("replace") ) {
                    script += "setTimeout(function() {window.location.replace('" + url + "'); }, 100);";
                }
                else if ( LocationType.equalsIgnoreCase("reload") ) {
                    script += "window.location.reload('" + url + "');";
                }
            }
            script += "</script>";

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(script);
            writer.close();
        }
        catch (Exception e) {}
    }
}
