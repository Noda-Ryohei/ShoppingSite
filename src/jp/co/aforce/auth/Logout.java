package jp.co.aforce.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html; charset=Shift_JIS");
//		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(true);
		session.invalidate();

		response.sendRedirect("/ShoppingSite/Login");
	}

}
