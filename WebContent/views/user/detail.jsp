<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>商品詳細</h2>
	<div id="img">
		<img src="/ShoppingSite/img/${product.fileName}" alt="NO IMAGE">
	</div>
	<div>
		<div id="description">
			<h3>${product.name}</h3>
<%-- 			<p>${product.categoryName}</p> --%>
			<p>
				<c:forEach var="cat" items="${categories}">
					<c:if test="${cat.id==product.categoryId}">
						${cat.name}
					</c:if>
				</c:forEach>
			</p>
			<p>${product.price}円</p>
			<p class="description">${product.description}</p>
		</div>
		
		<form action="/ShoppingSite/cart-add" method="post">
			<input type="hidden" name="product-id" value="${product.id}">
			<p>個数
				<select name="count">
					<option value="">選択してください。</option>
					<c:forEach var="i" begin="1" end="100">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>
			</p>
			<button type="submit" formaction="/ShoppingSite/list">商品一覧に戻る</button>
			<button type="submit" name="button-name" value="add">カートに追加</button>
			<p>${msg}</p>
		</form>
	</div>
</div>

<%@ include file="footer.jsp" %>