package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.CategoryBean;
import jp.co.aforce.beans.MemberBean;
import jp.co.aforce.beans.PaymentBean;
import jp.co.aforce.dao.CategoryDAO;
import jp.co.aforce.dao.MemberDAO;
import jp.co.aforce.dao.PaymentDAO;

@WebServlet("/login-check")
public class LoginCheck extends HttpServlet {
	
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String target = "login";
		response.sendRedirect(target);
		return;
		
	}


	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		//セッションの取得または開始
		HttpSession session = request.getSession();

		//遷移先
		String target = "login";

		//パラメータの取得
		String loginId = request.getParameter("login-id");
		String password = request.getParameter("password");

		//ログインＩＤ再入力の手間をなくすための処理
		session.setAttribute("loginId", loginId);

		//入力チェック
		if ("".equals(loginId) || "".equals(password)) {
			//未入力があるとき

			session.setAttribute("loginMsg", "未入力の項目があります。");

		} else {
			//すべて入力されているとき

			MemberDAO dao = new MemberDAO();
			MemberBean member = dao.search(loginId, password);

			if (member != null) {
				//ユーザ、管理者どちらかで認証成功。

				session.setAttribute("member", member);

				List<CategoryBean> categories = new CategoryDAO().getAll();
				List<PaymentBean> payments = new PaymentDAO().getAll();
				session.setAttribute("categories", categories);
				session.setAttribute("payments", payments);

				if (loginId.equals("admin")) {
					//管理者として認証
					session.setAttribute("login", "admin");
					session.removeAttribute("loginId");
					session.removeAttribute("loginMsg");

					target = (String) session.getAttribute("target");
					if (target==null || !target.contains("admin")) {
						target = "admin/list";
					}
					session.setAttribute("target", null);
					
					//カートを削除
					session.removeAttribute("cart");

				} else {
					//認証済みにセット
					session.setAttribute("login", "user");
					session.removeAttribute("loginId");
					session.removeAttribute("loginMsg");

					//本来のアクセス先へ飛ばす。
					target = (String) session.getAttribute("target");
					if (target==null || target.contains("admin")) {
						target = "list";
					}
					session.removeAttribute("target");

					//前回ログインした人と別の人だと、カートを削除
					String preUser = (String) session.getAttribute("preUser");
					if (preUser == null || !preUser.equals(member.getId())) {
						session.removeAttribute("cart");
					}
				}

			} else {
				//認証に失敗したらログイン画面に戻す。

				session.setAttribute("loginMsg", "認証に失敗しました。<br>再度、ログインＩＤとパスワードを入力してください。");
				session.setAttribute("loginId", loginId);
				target = "login";

			}

		}

		response.sendRedirect(target);
		session.removeAttribute("target");

		return;

	}

//	protected boolean authUser(String loginId, String password) {
//		//		//とりあえずユーザ名とパスワードが入力されていれば認証する。→　書き換えたから入力チェックの役割になった。
//		//		if ("".equals(loginId) || "".equals(password)) {
//		//			return false;
//		//		}
//
//		try {
//			MemberDAO dao = new MemberDAO();
//			MemberBean member = dao.search(loginId, password);
//
//			if (member != null) {
//				return true;
//			} else {
//
//				return false;
//			}
//		} catch (Exception e) {
//			return false;
//		}
//
//	}

}
