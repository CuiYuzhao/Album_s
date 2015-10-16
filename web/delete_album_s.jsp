<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>删除成功</title>
        <style>
        body{
        		background: url(img/2.jpg);
        		color: white;
        	}
        	#wrap{
        		padding-top:130px;
        		width:700px;
        		margin: auto;
        		text-align:center;
        	}
        	#deleteAlbum{
        		display: block;
        		width: 500px;
        		text-align: center;
        		font-size: 30px;
        		padding-bottom: 40px;
        	}
        	#albumValue{
        		width:280px;
        		height:25px;
        		line-height: 25px;
        		padding-left: 15px;
        		border-radius: 5px;
        		font-size: 20px;
        		outline: none;
        	}
        	#submit {
                    height: 20px !important;
                    cursor: pointer;
                    width: 80px;
                    display: block;
                    margin: auto;
                    margin-top: 15px;
                    border-radius: 5px;
                  }
        	#backindex{
        		display: block;
        		margin-top:20px;
        		color: white;
        		padding-top:80px;
        		
        	}
        </style>
    </head>
    <body>
    	<div id="wrap">
	        <%
	            String user_name = (String) session.getAttribute("user_name");
	            String Album_title = (String) session.getAttribute("Album_title");
	        %>
	        <h2>删除相册成功，请进行以下操作.......</h2>
	        <a href="delete_album.jsp"  style="color:white;text-decoration:underline;">继续删除</a>
	        <a href="home.jsp"  style="color:white;text-decoration:underline;">返回相册主页</a>
	      </div>
    </body>
</html>