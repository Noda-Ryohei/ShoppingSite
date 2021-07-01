<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
/* html { */
/* 	padding: 0; */
/* 	margin: 0; */
/* 	height: 100%; */
/* } */
body {
/* 	background-color: #dfe9e9; */
	width: 100%;
	margin: 0;
	padding: 0;
	text-align: center;
/* 	フッターを最下部に抑えておくための設定。　#contentのflex:1;と合わせて。 */
  display: flex;
  flex-flow: column;
  min-height: 100vh;
}

.login {
	width: 300px;
}



header {
	background-color: blue;
	color: #fff;
}
.title {
/* 	margin-right: auto; */
/* 	margin-left: auto; */
}
header a {
	color: #fff;
}
h1 a {
/* 	 color: #4f0000;  */
	 text-decoration: none;
/* 	 margin-top: 0; */
}
h1{
	color: #fff;
	margin-top: 0;
	margin-bottom: 0;
	
}

header .container {
	display: flex;
	justify-content: flex-end;
}
header .item {
	margin-right: 5px;
}
li {
	list-style: none;
}



#content {
	text-align: center;
	flex: 1;
}
form .container {
	display: flex;
	justify-content: flex-end;
	flex-wrap: wrap;
	width: 80%;
}
table {
	margin-left: auto; 
	margin-right: auto;
	width: 80%;
}
#img img {
	border: solid 1px #000;
	padding: 25px;
	background-color: #fff;
/* 	width: 50%; */
	width: 100px;
/* 	max-width: 200px; */
}
p.description {
	max-width: 300px;
	margin-right: auto;
	margin-left: auto;
}



footer{
	background-color: blue;
	color: #fff;
	height: 50px;
/* 	position: absolute; */
/*     bottom: 0; */
    width: 100%;
}
footer a {
	color: #fff;
}
footer .container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
}
footer .item {
	margin-right: 5px;
}

</style>