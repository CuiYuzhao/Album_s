<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>相册系统登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <style>
            a{
                color: white;
            }
        </style>
    </head>

    <body>

        <div class="page-container">
            <h1>图片相册管理系统</h1>
            
            <form id="login" name="login" action="LoginServlet" method="post">
            
                <input type="text" name="user_name" class="username" placeholder="请输入您的用户名！">
                <input type="password" name="user_password" class="password" placeholder="请输入您的用户密码！">
               <a href="home.jsp"><button type="submit" class="submit_button">登录</button></a>
                </br></br></br>
                 <a href="home.jsp">游客</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="register.jsp">注册</a>
                <div class="error"><span>+</span></div>
                
            </form>
            
        </div>
		
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js" ></script>
        <script src="assets/js/supersized.3.2.7.min.js" ></script>
        <script src="assets/js/supersized-init.js" ></script>
        <script src="assets/js/scripts.js" ></script>

    </body>
</div>
</html>

