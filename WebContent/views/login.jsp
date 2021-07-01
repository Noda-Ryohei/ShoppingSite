<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tobari Depart Online Store</title>

<!-- スタイルシートの読み込み -->
<!-- <link rel="stylesheet" href="/ShoppingSite/css/style.css"> -->
<%@ include file="/css/style.jsp" %>

<!-- jsファイルの読み込み -->
<script src="/ShoppingSite/js/confirm.js"></script>

</head>
<body>

<header>
<h1>トバリデパート オンラインストア</h1>
<!-- <a href="logout" onclick="return showConfirmDialog('ログアウト')">ログアウト</a> -->
<!-- <a href="cart-add">カート</a> -->
</header>

<div id="content">
	<h2>ログインページ</h2>
	
	<form action="/ShoppingSite/login-check" method="post">
		<table class="login">
			<tbody>
				<tr>
					<td>ログインID</td>
					<td><input type="text" name="login-id" value="${requestScope.loginId}"></td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td><input type="password" name="password"></td>
				</tr>
			</tbody>
		</table>
		<button type="submit">ログイン</button>
		<p>${msg}${loginMsg}</p>
	
	</form>

</div>

<footer>
<ul>
<!-- 	<li><a href="product-list">商品一覧</a></li> -->
<!-- 	<li><a href="../views/cart.jsp">カート</a></li> -->
	
</ul>
</footer>
</body>
</html>