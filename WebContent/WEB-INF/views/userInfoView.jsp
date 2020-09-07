<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
	<head>
	<style><%@include file="/WEB-INF//css/style.css"%></style>
		<meta charset="utf-8" />
		<title>Trang chủ</title>
	</head>
	<body>
		<div id="container">
			<div id="menu">
				<jsp:include page="menuchon.jsp"></jsp:include>
			</div>

			<div id="content">
				<div id="header">
					<jsp:include page="header.jsp"></jsp:include>
      				<center><jsp:include page="menu.jsp"></jsp:include></center>
      				<div class="crsector">
      				<h3>Hello: ${user.userName}</h3>
					User Name:<b>${user.userName}</b><br/> 
					Password: ${user.password } <br />
					</div>
				</div><!--#header-->
				<div class="call-to-action">
					</div><!--.call-to-action-->

				<div class="row">
					<div id="box1" class="col">		
					</div>
					<div id="box2" class="col">
					</div>
					<div id="box3" class="col">			
					</div>
				</div><!--.call-to-action-->

			</div><!--#content-->

			<div id="footer">
				
			</div><!--#footer-->

		</div><!--#container-->
	</body>
</html>












