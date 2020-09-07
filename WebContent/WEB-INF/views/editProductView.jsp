<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Sectors</title>
      <style><%@include file="/WEB-INF//css/style.css"%></style>
   </head>
   <body>
      <jsp:include page="header.jsp"></jsp:include>
      <jsp:include page="menu.jsp"></jsp:include>
 
      <center><h3>Edit Sectors</h3></center>
 
      <p style="color: red;">${errorString}</p>
 <div class="crsector">
      <c:if test="${not empty sector}">
         <form method="POST" action="${pageContext.request.contextPath}/editProduct">
            <input type="hidden" name="id" value="${sector.id}" />
            <table border="0">
               <tr>
                  <td>Id</td>
                  <td style="color:red;">${sector.id}</td>
               </tr>
               <tr>
                  <td>Name</td>
                  <td><input type="text" name="name" value="${sector.name}" /></td>
               </tr>
               <tr>
                  <td>Status</td>
                  <td><input type="text" name="status" value="${sector.status}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/productList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 </div>
      <jsp:include page="footer.jsp"></jsp:include>
 
   </body>
</html>

