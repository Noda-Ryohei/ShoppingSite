package jp.co.aforce.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.MemberBean;
import jp.co.aforce.dao.MemberDAO;

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response
			) throws IOException, ServletException {
		
		response.setContentType("text/html; charset=Shift_JIS");
//	    PrintWriter out = response.getWriter();

	    String user = request.getParameter("user");
	    String pass = request.getParameter("pass");

	    HttpSession session = request.getSession(true);
	    
	    boolean check = authUser(user, pass);
	    if (check) {
	    	//認証済みにセット
	    	session.setAttribute("login", "OK"); //ここは"user"とかにするかな。
	    	
	    	//本来のアクセス先へ飛ばす。
	    	String target = (String) session.getAttribute("target"); //targetがnullだった場合どうするか。
	    	if (target == null) {
	    		target = "/ShoppingSite/CustomAuth1";
	    	}
	    	response.sendRedirect(target);
	    
	    } else {
	    	//認証に失敗したら、ログイン画面に戻す。
	    	session.setAttribute("status", "Not Auth");
	    	response.sendRedirect("/ShoppingSite/Login");
	    }

	}
	
	protected boolean authUser(String user, String pass) {
		//とりあえずユーザ名とパスワードが入力されていれば認証する。→　書き換えたから入力チェックの役割になった。
		if (user == null 
				|| user.length() == 0 
				|| pass == null 
				|| pass.length() == 0
				) {
			return false;
		}
		
		try {			
			MemberDAO dao = new MemberDAO();
			MemberBean member = dao.search(user, pass);
			
			if (member.getId() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}

}
