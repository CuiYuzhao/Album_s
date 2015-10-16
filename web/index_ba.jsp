<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片相册管理系统</title>
<link rel="stylesheet" href="css/index.css">
</head>

<body>
		<%
            try {
                boolean isLogin = (boolean) session.getAttribute("isLogin");
                if (!isLogin) {
                    out.print("帐号或者密码错误！");
                }
            } catch (Exception e) {
            }
        %>
<form id="login" name="login" action="LoginServlet" method="post" >

    <h1 style="display:block;margin:auto;">登 录</h1>
    </br></br>
    <fieldset id="inputs">
        <input id="username" type="text" name="user_name" placeholder="账号" autofocus required>   

        <input id="password" type="password" name="user_password" placeholder="密码" required>
    </fieldset>

    <fieldset id="actions">

        <input type="submit" id="submit" value="登录">

        <a href="home.jsp">游客入口</a><a href="register.jsp">注册</a>

    </fieldset>
</form>

<!-- BSA AdPacks code -->

<script src="js/jq.min.js"></script>

<div style="clear:both"></div>
<br><br>
<div style="text-align:center">


</div>
</body>
</html>
