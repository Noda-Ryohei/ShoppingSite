<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>商品追加（管理者）</h2>

</div>
<p>${msg}</p>
<form action="/ShoppingSite/admin/register" method="post">
	<table>
		<tbody>
			<tr>
				<td>商品名*</td>
				<td><input type="text" name="name" value="${product.name}"></td>
			<tr>
			<tr>
				<td>値段*</td>
				<td><input type="text" name="price" value="${product.price}"></td>
			</tr>
			<tr>
				<td>在庫数*</td>
				<td><input type="text" name="stock" value="${product.stock}"></td>
			</tr>
			<tr>
				<td>商品説明</td>
				<td><textarea name="description" cols="30" rows="5">${product.description}</textarea></td>
			</tr>
			<tr>
				<td>カテゴリ*</td>
				<td>
					<select name="category-id">
						<option value="">選択してください。</option>
						<c:forEach var="cat" items="${categories}">
							<c:choose>	
								<c:when test="${cat.id==product.categoryId}">
									<option value="${cat.id}" selected>${cat.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${cat.id}">${cat.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>画像ファイル名</td>
				<td><input type="text" name="file-name"></td>
			</tr>
			
		</tbody>
	
	</table>	
	
	<button type="submit" name="button-name" value="back"
			onclick="return showConfirmDialog('入力を破棄')">戻る</button>
	<button type="submit" name="button-name" value="register"
			onclick="return showConfirmDialog('追加')">追加</button>
	
</form>

<%@ include file="footer.jsp" %>