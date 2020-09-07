<%@page import="Controller.MyUtils"%>
<%@page import="Controller.DBUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Sectors"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<style><%@include file="/WEB-INF//css/style.css"%></style>
<meta charset="utf-8">
<title>Sectors List</title>
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
		<!--  <form action="${pageContext.request.contextPath}/productList" method="POST">
		<input style="padding: 5px 10px" type="text" name="txtid" value=""><button tystyle="background: green; color: white;">Search</button>
		<input type="text" name="txtid" value=""><br>
		<input type="submit" value="search" name="action">
		</form>
		<table border="1">
			<tr>
				<td>Ma loai hang</td>
				<td>Ten loai hang</td>
				<td>Trang thai</td>
			</tr>
			<c:forEach items="${productList1}" var="sect" >
        
          <tr>
             <td>${sect.id}</td> 
             <td>${sect.name}</td>
    		 <td>${sect.status}</td>
             
			               
          </tr>
  
       </c:forEach>
		</table>-->
	</div>
 				<div id="header">
				
      				<center><jsp:include page="menu.jsp"></jsp:include></center>
				</div>
    <center><h3>Sectors List</h3></center>
  	<form action="${pageContext.request.contextPath}/productList"  method="post">
	    <button  type="button" onclick="functiona()">Create</button>
	    <button  type="button" name="action" value="Export" onclick="exportsector()">Export</button>
		<!-- <a href="${pageContext.request.contextPath}/expor">EXPORT</a>-->
		<button type="button"  id="btnedit" onclick="functionb()">Edit</button>
		<button type="button" onclick="thongbao()" id = "selectid">Delete</button>  
	</form>
    <p style="color: red;">${errorString}</p>

    <table id="tablesector" border="1" cellpadding="5" cellspacing="1" >
       <tr>
       	  <th></th>
          <th>Mã loại hàng</th>
          <th>Tên loại hàng</th>
          <th>Trạng thái</th>
          <th>Delete</th>
          <th>Detail</th>
       </tr>
        <form action="${pageContext.request.contextPath}/productList" method="post">
        <input type="text" name="txtid" value="" width="50%">
		<input type="submit" value="search" name="action">
		</form>
       <c:forEach items="${productList}" var="sect" >
        
          <tr>
          	 <!--  <td> <a href="productList?id=${sect.id}"><input type="checkbox" id = "id" value="${sect.id}" class="eidtsr" ></td>-->
          	 <td><input type="checkbox" ><a href="productList?id=${sect.id}"></button> </td>
             <td><a onclick="functionb()"  href="productList?id=${sect.id}" >${sect.id}</td> 
             <td><input type="hidden" id="name${sect.getId()}" value="${sect.name}" >${sect.name}</td>
            <!--  <td><img src="data:image/jpeg;base64,${sect.image}" width="200" height="200"/></td>--> 
    		 <td><input type="hidden" id="status${sect.getId()}" value="${sect.status}">${sect.status}</td>
             <td>
                <a onclick="if (!confirm('Bạn có muốn xóa không?')) { return false }" href="deleteProduct?id=${sect.id}">Delete</a>
             </td>  
			 <td>Detail</td>              
          </tr>
  
       </c:forEach>
   
    </table>
    		<c:forEach var="j" begin="1" step="1" end="${totalpage}">
			
				<c:if test="${page !=j}">
					<a href="${pageContext.request.contextPath}/productList?page=${j}">${j}</a>|
				</c:if>
				<c:if test="${page ==j }">
					${j }
				</c:if>
			</c:forEach>
    
    
    <dialog id="eidtsr">
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
               	   <td>Image</td>
               	   <td><img src="data:image/jpeg;base64,${sector.image}" width="200" height="200"/></td>
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
</dialog>
    
    <dialog id="hopthoai"><p style="color: red;">${errorString}</p>
       
       <div class="crsector">
		    <center><h2>Create Sectors</h2></center>
		    <p style="color: red;">${errorString}</p>
		    <form method="POST" action="${pageContext.request.contextPath}/createProduct" enctype="multipart/form-data">
		       
		        <input type="text" name="id" placeholder="Nhập mã loại hàng...">
		        <input type="text" name="name" placeholder="Nhập tên loại hàng...">
		        <input type="file" name="image"    placeholder="Chọn file ảnh...">
		        <input type="text" name="status" placeholder="Nhập trạng thái...">
		        <center><input type="submit" value="Submit">
		        <a href="productList">Cancel</a></center>
		    </form>
		</div></dialog>
    
    <script type="text/javascript">
    function functiona(){
        var x = document.getElementById("hopthoai");
        x.showModal();
    }
    
    function functionb(){
       // var x = document.getElementsByClassName("eidtsr");
        var x = document.getElementById("eidtsr");
       /* var sp = 0;
        var idedit;
        for(var i=0;i<x.length;i++){
        	if(x[i].checked){
        		idedit   = x[i].value;
        		sp++;
        	}
        }
        
          var id1 = document.getElementById("id"+idedit).value;
		  var name1 = document.getElementById("name"+idedit).value;
		  var status1 = document.getElementById("status"+idedit).value;
		
		  var updateDialog = document.getElementById('editupdate');
		  
		  updateDialog.showModal();
		 
		  
		  var id = document.getElementById("idup");
		  id.value = id1;
		  var name = document.getElementById("nameup");
		  name.value = name1;
		  var status = document.getElementById("statusup");
		  status.value = status1;*/
		  
        x.showModal();
    }
    
    function timkiem(){
    	
    }
    
    function exportsector()
    {
  	   var htmltable= document.getElementById('tablesector');
        var html = htmltable.outerHTML;
        window.open('data:application/vnd.ms-excel,' + encodeURIComponent(html));
        
        
        
    }
    
    function funback(){
    	var updateDialog = document.getElementById('editupdate');
		  updateDialog.close();
    }
    </script>
 </div>
 </div>
 
 
<!-- hop thoai xoa -->
<script type="text/javascript">
	function thongbao() {
		
		 var checkbox = document.getElementsByClassName("selectid");
		 var sp = 0;
		 var deleteall = "id:";
		  
		 for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					deleteall += checkbox[i].value+',';
					sp++;
				}
			}
		  if(sp == 0)
			  alert("Bạn chưa chọn sản phẩm nào");
		  else {
			  var idDelete = document.getElementById("idDelete");
			  idDelete.value = deleteall;
			  var deleteDialog = document.getElementById('deleteDialog');
			  deleteDialog.showModal();
		  }
		 
		 var result = confirm("Bạn có muốn xóa không?");
		 if(result)  
            return true;
         
		 else
			 return false;
	}
</script>

 </body>
</html>

 