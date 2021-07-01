package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;

@WebServlet("/cart")
public class Cart extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションの取得、または開始
		HttpSession session = request.getSession();

		//遷移先の設定
		String target = "/views/user/cart.jsp"; //コンテキストルートからのパス（頭の/を外せば、相対パスになる。）

		//セッションから、カート（商品ビーンとその個数のマップ）を取得。nullの場合は新しくカートを生成してセッションに追加。
		Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<ProductBean, Integer>();
			session.setAttribute("cart", cart);
		}
		

		//合計金額の計算処理
		int total = 0;

		for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {

			int price = Integer.parseInt(entry.getKey().getPrice());
			int count = entry.getValue();
			total += price * count;

		}
		session.setAttribute("total", Integer.toString(total));
		

		request.getRequestDispatcher(target).forward(request, response);

	}
	
}
