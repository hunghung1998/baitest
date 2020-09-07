<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style><%@include file="/WEB-INF//css/style.css"%></style>
<title>Create Sectors</title>
</head>
<body>
	<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      
   </head>
   <body>
    	
      <jsp:include page="header.jsp"></jsp:include>
      <jsp:include page="menu.jsp"></jsp:include>
   
      <p style="color: red;">${errorString}</p>
       
       <div class="crsector">
		    <center><h2>Create Sectors</h2></center>
		    <form method="POST" action="${pageContext.request.contextPath}/createProduct">
		        <label for="fname">Nhập mã loại hàng</label>
		        <input type="text" name="id" value="${product.id}" placeholder="Nhập mã loại hàng...">
		 
		        <label for="lname">Nhập tên loại hàng</label>
		        <input type="text" name="name" value="${product.name}" placeholder="Nhập tên loại hàng...">
		 
		        <label for="country">Nhập trạng thái</label>
		        <input type="text" name="status" value="${product.status}" placeholder="Nhập trạng thái...">

		        <input type="submit" value="Submit">
		        <a href="productList">Cancel</a>
		    </form>
		</div>
       
      <jsp:include page="footer.jsp"></jsp:include>
       
   </body>
</html>
</body>
</html>