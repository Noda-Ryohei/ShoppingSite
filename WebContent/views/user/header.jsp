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
	<h1><a href="/ShoppingSite/list">トバリデパートオンラインストア</a></h1>
	<div class="container">
		<div class="item">ようこそ、${member.name}さん!</div>
		<div class="item"><a href="/ShoppingSite/logout" onclick="return showConfirmDialog('ログアウト')">ログアウト</a></div>
		<div class="item"><a href="/ShoppingSite/list">商品一覧</a></div>
		<div class="item"><a href="/ShoppingSite/history">購入履歴</a></div>
		<div class="item"><a href="/ShoppingSite/cart">カート</a></div>
	</div>
</header>