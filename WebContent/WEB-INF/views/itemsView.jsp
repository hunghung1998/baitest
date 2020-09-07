<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF//css/style.css"%></style>
<meta charset="utf-8">
<title>Items ListView</title>
</head>
<body>
	<div id="container">	
    <div id="menu">
				<jsp:include page="menuchon.jsp"></jsp:include>
			</div>
 	<div id="content">
 	<div style="float: right; padding: 10px; text-align: right;">
		<!-- User store in session with attribute: loginedUser -->
		Hello <b>${loginedUser.userName}</b> <br /> 
	</div>
 				<div id="header">
				
      				<center><jsp:include page="menu.jsp"></jsp:include></center>
				</div>
    <center><h3>Items ListView</h3></center>
    <form action="${pageContext.request.contextPath}/itemList"  method="post">
	    <button  type="button" onclick="createitem()">Create</button>
	    <button type="button" onclick="createanh()">AddAnh</button>
	    <button  type="button" name="action" value="Export" onclick="exportsector()">Export</button>
		<!-- <a href="${pageContext.request.contextPath}/expor">EXPORT</a>-->
		<button type="button"  id="btnedit" onclick="functionb()">Edit</button>
		<button type="button" onclick="thongbao()" id = "selectid">Delete</button>  
	</form>
	<form action="${pageContext.request.contextPath}/itemList" method="post">
        <input type="text" name="txtid" value="" width="50%">
		<input type="submit" value="search" name="action">
		</form>
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Mã mặt hàng</th>
          <th>Tên mặt hàng</th>
          <th>Tên nhà cung cấp</th>
          <th>Mã loại hàng</th>
          <th>Gía bán</th>
          <th>Đơn vị tính</th>
          <th>Trạng thái</th>
          <th>Delete</th>
       </tr>
		<c:forEach items="${employeeList}" var="it">
			<tr>
				<td>${it.id}</td>
				<td>${it.name}</td>
				<td>${it.sp_id }</td>
				<td>${it.sec_id }</td>
				<td>${it.price }</td>
				<td>${it.unit }</td>
				<td>${it.status}</td>
				<td><a onclick="if (!confirm('Bạn có muốn xóa không?')) { return false }" href="deleteItems?id=${it.id}">Delete</a></td>  
			</tr>
		</c:forEach>
		</table>
		
		<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ID</th>
          <th>IMAGES</th>
       </tr>
		<c:forEach items="${view}" var="it">
			<tr>
				<td>${it.user}</td>
			
				<td><img src="data:image/jpeg;base64,${it.image}" width="200" height="200"/></td>
			</tr>
		</c:forEach>
		</table>
		
		<c:forEach var="j" begin="1" step="1" end="${totalpage}">
			
				<c:if test="${page !=j}">
					<a href="${pageContext.request.contextPath}/itemList?page=${j}">${j}</a>|
				</c:if>
				<c:if test="${page ==j }">
					${j }
				</c:if>
			</c:forEach>
		<div>	
		</div>
	</div>
</div>
		<dialog id="create"><p style="color: red;">${errorString}</p>
       <div class="crsector">
		    <center><h2>Create Sectors</h2></center>
		    <p style="color: red;">${errorString}</p>
		    <form method="POST" action="${pageContext.request.contextPath}/insertItems">
		       
		        <input type="text" name="id"      placeholder="Nhập mã mặt hàng...">
		        <input type="text" name="name"    placeholder="Nhập tên mặt hàng...">
		        <input type="text" name="sp_id"   placeholder="Nhập mã nhà cung cấp...">
		        <input type="text" name="sec_id"  placeholder="Nhập mã loại hàng...">
		        <input type="text" name="price"   placeholder="Nhập giá bán...">
		        <input type="text" name="unit"    placeholder="Nhập đơn vị tính...">
		        <input type="text" name="status"  placeholder="Nhập trạng thái...">
		        <center><input type="submit" value="Submit">
		        <a href="itemList">Cancel</a></center>
		    </form>
		</div></dialog>
		
		<dialog id="createanh"><p style="color: red;">${errorString}</p>
       <div class="crsector">
		    <center><h2>Create Anh</h2></center>
		    <p style="color: red;">${errorString}</p>
		    <form method="POST" action="${pageContext.request.contextPath}/insertanh" enctype="multipart/form-data">
		       
		        <input type="text" name="user"      placeholder="Nhập mã ảnh...">
		        <input type="file" name="base"    placeholder="Chọn file ảnh...">
		        <center><input type="submit" value="Submit">
		        <a href="itemList">Cancel</a></center>
		    </form>
		</div></dialog>
<script type="text/javascript">
	function createitem() {
		var x = document.getElementById("create");
        x.showModal();
	}
	
	function createanh() {
		var x = document.getElementById("createanh");
        x.showModal();
	}
	
	function thongbao() {
		var result = confirm("Bạn có muốn xóa không?");
		 if(result)  
           return true;
        
		 else
			 return false;
	}
</script>		
</body>
</html>