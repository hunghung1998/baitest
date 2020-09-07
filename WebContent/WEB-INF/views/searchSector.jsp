<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sectors List   </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/searchSector" method="get">
		<!-- <input style="padding: 5px 10px" type="text" name="txtid" value=""><button tystyle="background: green; color: white;">Search</button> -->
		<input type="text" name="txtid" value=""><br>
		<input type="submit" value="search" name="action">
		</form>
		  <table border="1">
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Status</td>
			</tr>
			<c:if test="${not empty listid}">
				<form action="${pageContext.request.contextPath}/searchSector" method="get">
					<tr>
						<td>${listid.id}</td>
						<td>${listid.name}</td>
						<td>${listid.status}</td>
					</tr>
				</form>
			</c:if>
		</table>

</body>
</html>