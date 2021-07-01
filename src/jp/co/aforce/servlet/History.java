package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/history")
public class History extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		String target = "views/user/history.jsp";
		request.getRequestDispatcher(target).forward(request, response);
		
	}
}
