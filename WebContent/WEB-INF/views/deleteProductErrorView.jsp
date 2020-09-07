<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Delete Product</title>
 </head>
 
 <body>
 
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="menu.jsp"></jsp:include>
    
    
    <h3>Delete Sectors</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="productList">Sectors List</a>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
 </body>
</html>