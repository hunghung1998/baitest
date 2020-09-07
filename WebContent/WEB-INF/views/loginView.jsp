<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập vào website</title>
        <meta charset="UTF-8">
        <style><%@include file="/WEB-INF/css/style.css"%></style>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>		
        </header>
        <main>
            <div class="container">
            <div class="login-form">
                <form method="POST" action="${pageContext.request.contextPath}/login">
                    <center><h1>Đăng nhập vào website</h1></center>
                    <div class="input-box">
                        <i ></i>
                        <input type="text" placeholder="Nhập username" name="userName" value="${user.userName}">
                    </div>
                    <div class="input-box">
                        <i ></i>
                        <input type="password" placeholder="Nhập mật khẩu" name="password" value="${user.password}">
                    </div>
                    <div class="btn-box">
                        <button type="submit">
                            Đăng nhập
                        </button>
                         <p style="color: red;">${errorString}</p>
                    </div>
                </form>
            </div>
            </div>
        </main>
        <footer>
            <div class="container">
            
            </div>
        </footer>
    </body>
</html>