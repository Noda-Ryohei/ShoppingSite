<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>カート一覧</h2>

</div>

<div id="content">
	<form action="/ShoppingSite/cart-add" method="post">
		<div>
			<c:choose>
				<c:when test="${cart.size()>0}">
					<p>${cart.size()}種類の商品があります。</p>
					<hr>
				</c:when>
				<c:otherwise>
					<p>カートに商品がありません。</p>
					<button type="submit" formaction="/ShoppingSite/list">買い物を続ける</button>
				</c:otherwise>
			</c:choose>
			<table>
				<tbody>
					<c:forEach var="entry" items="${cart}" >
<%-- 						<input type="hidden" name="product-id" value="${cart.key.id}"><!-- 何に使うん？ --> --%>
						<tr>
							<td><img src="/ShoppingSite/img/${entry.key.fileName}" alt="NO IMAGE" height="64"></td>
							<td>${entry.key.name}</td>
							<td>${entry.key.price}円</td>
							<td>個数：${entry.value}個
								<select name="count${entry.key.id}">
									<c:forEach var="i" begin="1" end="100">
										<c:choose>
											<c:when test="${i==entry.value}">
												<option value="${i}" selected>${i}</option>
											</c:when>
											<c:otherwise>
												<option value="${i}">${i}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							<td>
								<button type="submit" formaction="/ShoppingSite/cart-change" name="product-id" value="${entry.key.id}">個数変更</button>
							</td>
							<td>
								<button type="submit" formaction="/ShoppingSite/cart-remove" name="product-id" value="${entry.key.id}"
								onclick="return showConfirmDialog('カートから削除')">カートから削除</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
		
		<c:choose>
			<c:when test="${cart.size()>0}">
				<div>
					<p>合計金額：${total}円</p>
				</div>
				<div>
					<button type="submit" formaction="/ShoppingSite/list">買い物を続ける</button>
		<!-- 			<button type="submit" name="button-name" value="reload" >再計算</button> -->
					<button type="submit" formaction="/ShoppingSite/confirm">注文へ</button>
				</div>
			</c:when>
		</c:choose>
	</form>
</div>

<%@ include file="footer.jsp"%>