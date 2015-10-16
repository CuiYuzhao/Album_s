<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>页面跳转</title>
        <style>
        body{
        		background: url(img/1.jpg);
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
    <%
        String user_name = (String) session.getAttribute("user_name");
        String Album_title = (String) session.getAttribute("Album_title");
        String Album_isopen = (String) session.getAttribute("Album_isopen");
        //out.print(user_name);
        //out.print(Album_title);
    %>
    <body>
    	<div id="wrap">
	        <h1>上传相片成功，请继续完成一下操作......</h1>
	        <div style="height:30px;">返回：<a style="color:white;text-decoration:underline;" href="showphoto.jsp?Album_user=<%=user_name%>&Album_title=<%=Album_title%>&Album_isopen=<%=Album_isopen%>"><%=Album_title%></a>相册
	        </div>
	        <a style="color:white;text-decoration:underline;" href="upload.jsp?Album_user=<%=user_name%>&Album_title=<%=Album_title%>">继续上传</a>
	        <a href="home.jsp"  style="color:white;text-decoration:underline;">相册主页</a>
       </div>
    </body>
</html>
