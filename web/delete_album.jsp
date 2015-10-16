<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>删除相册</title>
        <style>
        	body{
        		background: url(img/1.jpg);
        		color: white;
        	}
        	#wrap{
        		padding-top:130px;
        		width:500px;
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
        		line-height: 15px;
        		padding-left: 25px;
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
    <%
        String user_name = (String) session.getAttribute("user_name");
        
    %>
    <body>
    	<div id="wrap">
	        <span id="deleteAlbum">删除相册</span>
	        <form name="dreate" action="DeleteAlbumServlet" method="post">
	            删除相册主题：<input type="text" name="album_title_d" id="albumValue" />
	            <input type="submit" value="删除" id="submit"/>
	        </form>
	        <a href="home.jsp" id="backindex">返回主页</a>
	    </div>
    </body>
</html>
