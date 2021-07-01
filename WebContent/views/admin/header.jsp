<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(Admin)Tobari Depart Online Store</title>

<!-- スタイルシートの読み込み -->
<!-- <link rel="stylesheet" href="/ShoppingSite/css/style.css"> -->
<%@ include file="/css/style.jsp" %>

<!-- jsファイルの読み込み -->
<script src="/ShoppingSite/js/confirm.js"></script>
</head>
<body>

<header>
	<div class="title">
		<h1><a href="/ShoppingSite/admin/list">（管理者）トバリデパート オンラインストア</a></h1>
	</div>
	<div class="container">
		<div class="item"><a href="/ShoppingSite/admin/category">カテゴリ管理</a></div>
		<div class="item"><a href="/ShoppingSite/admin/payment">支払方法管理</a></div>
		<div class="item"><a href="/ShoppingSite/admin/upload">商品画像アップロード</a></div>
		<div class="item"><a href="/ShoppingSite/admin/history">注文履歴管理</a></div>
		<div class="item"><a href="/ShoppingSite/admin/list">商品一覧</a></div>
		<div class="item"><a href="/ShoppingSite/logout" onclick="return showConfirmDialog('ログアウト')">ログアウト</a></div>
	</div>
</header>
