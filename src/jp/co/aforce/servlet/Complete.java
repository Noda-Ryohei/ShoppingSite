package jp.co.aforce.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.beans.MemberBean;
import jp.co.aforce.beans.OrderBean;
import jp.co.aforce.beans.ProductBean;
import jp.co.aforce.dao.OrderDAO;

@WebServlet(urlPatterns = { "/complete" })
public class Complete extends HttpServlet {

	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("product-list");
	}

	@SuppressWarnings("unchecked")
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッションの取得、または開始（開始は既にされているはずなんだけどな）
		HttpSession session = request.getSession();

		//遷移先の設定
		String target = "/views/user/complete.jsp";

		//セッションから、カートと合計金額を取得
		Map<ProductBean, Integer> cart = (Map<ProductBean, Integer>) session.getAttribute("cart");
		//		String totalStr = (String) session.getAttribute("total");
		MemberBean member = (MemberBean) session.getAttribute("member");

		//各パラメータの取得
		String deliveryName = request.getParameter("delivery-name");
		String postalCode = request.getParameter("postal-code");
		String deliveryAddress = request.getParameter("delivery-address");
		String paymentId = request.getParameter("payment-id");

		//商品番号、日付の生成
		LocalDateTime datetime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String id = "X" + format.format(datetime);
		format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = format.format(datetime);

		//OrderBeanの作成、値の格納
		OrderBean order = new OrderBean();
		order.setId(id);
		order.setDate(date);
		order.setCart(cart);
		order.setMemberId(member.getId());
		order.setDeliveryName(deliveryName);
		order.setPostalCode(postalCode);
		order.setDeliveryAddress(deliveryAddress);
		order.setPaymentId(paymentId);

		OrderDAO dao = new OrderDAO();

		//入力値チェック
		if (!dao.inputCheck(order)) {

			request.setAttribute("msg", "正しく入力されていない項目があります。");
			request.setAttribute("order", order);
			target = "confirm";

		} else {
			//入力値チェックが通ったとき
			
			//挿入処理を実行
			if (dao.insert(order)) {
				//挿入処理が成功したとき

				//注文内容確認用にリクエストにカートを設定、セッションからカートを除去。
				request.setAttribute("cart", cart);
				session.removeAttribute("cart");

				//合計金額についても同様。
				Object total = session.getAttribute("total");
				request.setAttribute("total", total);
				session.removeAttribute("total");

				//注文番号を設定
				request.setAttribute("orderId", id);

			} else {
				//挿入処理が失敗したとき

				session.setAttribute("msg", "購入手続き中にエラーが発生しました。");
				target = "confirm";
				
				response.sendRedirect(target);
				return;

			}
			
		}

		request.getRequestDispatcher(target).forward(request, response);

	}
}
