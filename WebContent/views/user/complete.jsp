<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
	<h2>注文完了</h2>
	<p>注文番号：${orderId}</p>
	<a href="/ShoppingSite/list">トップへ</a>
	
	<div>
		<p>注文内容</p>
		<p>合計金額：${total}円（内税：${Math.floor(total*10/110)}円）</p>
		<hr>
		<table>
			<c:forEach var="obj" items="${cart}">
			<tr>
				<td>${obj.key.name}</td>
				<td>${obj.key.price}円</td>
				<td>${obj.value}個</td>
				<td>小計：${obj.key.price*obj.value}円</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>

<%@ include file="footer.jsp" %>