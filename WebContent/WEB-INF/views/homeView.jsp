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
      				<jsp:include page="userInfoView.jsp"></jsp:include>
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





