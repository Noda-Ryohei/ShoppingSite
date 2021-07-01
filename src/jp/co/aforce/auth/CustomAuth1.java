package jp.co.aforce.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomAuth1")
public class CustomAuth1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

//        String target = request.getRequestURI();
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null){
//            /* まだ認証されていない */
//            session = request.getSession(true);
//            session.setAttribute("target", target);
//
//            response.sendRedirect("/ShoppingSite/Login");
//        }else{
//            Object loginCheck = session.getAttribute("login");
//            if (loginCheck == null){
//                /* まだ認証されていない */
//                session.setAttribute("target", target);
//                response.sendRedirect("/ShoppingSite/Login");
//            }
//        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>ユーザー認証テスト</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<p>テストページ1</p>");

        out.println("<p><a href=\"/ShoppingSite/CustomAuth2\">テストページ2へ</a></p>");

        out.println("<p><a href=\"/ShoppingSite/Logout\">ログアウト</a></p>");

        out.println("</body>");
        out.println("</html>");
    }


}
