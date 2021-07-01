package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.ProductBean;

@WebServlet(urlPatterns = { "/cart-remove" })
public class CartRemove extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションの取得、または開始。
		HttpSession session = request.getSession();

		//遷移先の設定
		String target = "cart";

		//セッションからカートを取得。（nullの場合は？）
		Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) session.getAttribute("cart");

		//商品番号を取得
		String id = request.getParameter("product-id");

		//カートから商品番号が一致するProductBeanを探して、一致したらカートからremoveする。
		for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
			if (entry.getKey().getId().equals(id)) {
				cart.remove(entry.getKey());
			}
		}

		//合計金額の計算処理
//		int total = 0;
//
//		for (Map.Entry<ProductBean, Integer> entry : cart.entrySet()) {
//
//			int price = Integer.parseInt(entry.getKey().getPrice());
//			int count = entry.getValue();
//			total += price * count;
//
//		}
//		session.setAttribute("total", Integer.toString(total));
		
		response.sendRedirect(target);
		
	}

}
