<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>注文確認（お客様情報入力）</h2>

	<div>
		<table>
			<tbody>
				<c:forEach var="obj" items="${cart}">
					<input type="hidden" name="product-id" value="${cart.key.id}"><!-- 何に使うん？ -->
					<tr>
						<td><img src="img/${obj.key.fileName}" alt="img" height="64"></td>
						<td>${obj.key.name}</td>
						<td>${obj.key.price}円</td>
						<td>個数：${obj.value}個</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>合計金額：${total}円</p>
	</div>
	<hr>
	<div>
		<form action="/ShoppingSite/complete" method="post">
			<table>
				<tbody>
					<tr>
						<td>氏名*</td>
						<td><input type="text" name="delivery-name" value="${order.deliveryName}"></td>
					</tr>
					<tr>
						<td>お届け先郵便番号*</td>
						<td>〒<input type="text" name="postal-code" value="${order.postalCode}"></td>
					</tr>
					<tr>
						<td>お届け先住所*</td>
						<td><textarea name="delivery-address" cols="20" rows="3">${order.deliveryAddress}</textarea></td>
					</tr>
					<tr>
						<td>お支払方法*</td>
						<td>
							<select name="payment-id">
								<option value="" selected>選択してください。</option>
								<c:forEach var="pay" items="${payments}">
									<c:choose>
										<c:when test="${pay.id==order.paymentId}">
											<option value="${pay.id}" selected>${pay.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${pay.id}">${pay.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>				
			</table>
			<button type="submit" formaction="/ShoppingSite/cart">カートに戻る</button>
			<button type="submit"
				onclick="return showConfirmDialog('注文を確定')">注文確定</button>
			<p>${msg}</p>
		</form>
	</div>
	
</div>

<%@ include file="footer.jsp" %>