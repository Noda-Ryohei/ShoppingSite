<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>商品一覧（管理者）</h2>

	<form action="/ShoppingSite/admin/list" method="post">
	
		<div class="container">
			<div class="item">
				キーワードで検索<input type="text" name="keyword" value="${keyword}">
				<button type="submit" name="button-name" value="search">検索</button>
			</div>
			<div class="item">
				並べ替え
				<select name="sort">
					<option value="" selected>選択してください。</option>
					<option value="price-asc">値段が安い順</option>
					<option value="price-desc">値段が高い順</option>
				</select>
				<button type="submit" name="button-name" value="sort">並べ替え</button>
			</div>
			<div class="item">
				<button type="submit" name="button-name" value="reload">更新</button>
			</div>
			<div class="item">
				<button type="submit" name="button-name" value="register">追加</button>
			</div>
		</div>

		<table>			
			<tbody>
				<c:forEach var="product" items="${list}">
					<tr>
						<td><img src="/ShoppingSite/img/${product.fileName}" alt="NO IMAGE" height="64"></td>
						<td>${product.name}</td>
						<td>
							<c:forEach var="cat" items="${categories}">
								<c:if test="${cat.id==product.categoryId}">
									${cat.name}
								</c:if>
							</c:forEach>
						</td>
						<td>${product.price}円</td>
						<td>${product.stock}個</td>
<%-- 						<td style="width: 40%">${product.description}</td> --%>
						<td>
							<button type="submit" formaction="/ShoppingSite/admin/detail" name="product-id" value="${product.id}">詳細</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</form>

</div>

<%@ include file="footer.jsp" %>