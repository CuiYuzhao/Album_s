<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户注册</title>
    </head>
    <body>
        <form name="login" action="RegisterServlet" method="post">
            <p>帐号: <input type="text" name="user_name" /></p>
            <p>密码: <input type="password" name="user_password" /></p>
            <input type="submit" value="注册" />
            <input type="reset" value="重置" />
            <a href="index.jsp">登陆</a>
            <a href="home.jsp">游客入口</a>
        </form>
    </body>
</html> --%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
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
<form id="login" name="login" action="RegisterServlet" method="post">

    <h1>注 册</h1>

    <fieldset id="inputs">
        <input id="username" type="text" name="user_name" placeholder="账号" autofocus required>   

        <input id="password" type="password" name="user_password" placeholder="密码" required>
    </fieldset>

    <fieldset id="actions">

        <input type="submit" id="submit" value="注册">

        <a href="home.jsp">游客入口</a><a href="index.jsp">登录</a>

    </fieldset>
</form>



<!-- BSA AdPacks code -->

<script src="js/jq.min.js"></script>

<script>


</script>



<div style="clear:both"></div>
<br><br>
<div style="text-align:center">


</div>
</body>
</html>
