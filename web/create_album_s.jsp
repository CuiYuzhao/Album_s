<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>创建成功</title>
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
                a{
                    color: white;
                }
        </style>
    </head>
   
    <body>
        <div id="wrap">
        <%
            String user_name = (String) session.getAttribute("user_name");
            String Album_title = (String) session.getAttribute("Album_title");
            //out.print(user_name);
            //out.print(Album_title);
            //session.setAttribute("Album_user",user_name);
            //session.setAttribute("Album_title",Album_title);
        %>
        <h2>创建相册成功，请进行以下操作.......</h2>
        </br></br></br></br>
        <a href="upload.jsp?Album_user=<%=user_name%>&Album_title=<%=Album_title%>">上传相片</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="home.jsp">返回相册主页</a>
        </div>
    </body>
</html>
