package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/confirm"})
public class Confirm extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException {
		
		//セッションの取得、または開始
//		HttpSession session = request.getSession();
		
		//遷移先の設定
		String target = "/views/user/confirm.jsp";
		
		//カートと合計金額の取得？？
		//セッションに入ってるしそのままでいいじゃん？
		
		request.getRequestDispatcher(target).forward(request, response);
		
		
	}
}
