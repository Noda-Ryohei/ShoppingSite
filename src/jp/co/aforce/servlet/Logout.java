package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.MemberBean;

@WebServlet(urlPatterns = { "/logout" })
public class Logout extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//遷移先の設定
		String target = "login";

		//		if (session.getAttribute("member") != null) {
		//			session.removeAttribute("member");
		//			request.setAttribute("msg", "ログアウトしました。");
		//		} else {
		//			request.setAttribute("msg", "ログアウトに失敗しました。");
		//		}

		if (session.getAttribute("login") != null) {
			//ログインしていたとき

			//前回のユーザとしての情報を保存
			MemberBean member = (MemberBean) session.getAttribute("member");
			if (member != null) {
				session.setAttribute("preUser", member.getId());
			}

			//ログイン情報と合わせて、不要なセッションを破棄。
			session.removeAttribute("login");
			session.removeAttribute("member");
			session.removeAttribute("cateogories");
			session.removeAttribute("payments");
			session.removeAttribute("product");
			session.removeAttribute("list");
			session.removeAttribute("msg");

			//言伝
			session.setAttribute("loginMsg", "ログアウトしました。");

		} else {
			//ログインしていなかったとき

			//言伝
			session.setAttribute("loginMsg", "ログインしていません。");

		}

		//		request.getRequestDispatcher(target).forward(request, response);
		response.sendRedirect(target);

	}

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}
}
