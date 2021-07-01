package jp.co.aforce.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomAuth2")
public class CustomAuth2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>ユーザー認証テスト</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<p>テストページ2</p>");

        out.println("<p><a href=\"/ShoppingSite/CustomAuth1\">テストページ1へ</a></p>");

        out.println("<p><a href=\"/ShoppingSite/Logout\">ログアウト</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}