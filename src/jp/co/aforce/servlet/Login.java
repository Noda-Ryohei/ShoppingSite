package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login" })
public class Login extends HttpServlet {
	
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		doPost(request, response);
		
	}

	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//セッションの開始（もしくは取得）
		HttpSession session = request.getSession();
		
		String loginMsg = (String) session.getAttribute("loginMsg");
		String loginId = (String) session.getAttribute("loginId");
		
		request.setAttribute("loginMsg", loginMsg);
		request.setAttribute("loginId", loginId);
		
		session.removeAttribute("loginMsg");
//		session.removeAttribute("loginId");
		
		//遷移先の設定
		String target = "/views/login.jsp";
		
		request.getRequestDispatcher(target).forward(request, response);

	}
	
}
