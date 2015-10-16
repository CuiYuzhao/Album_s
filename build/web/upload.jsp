<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String user_name = (String) request.getParameter("Album_user");
        String Album_title = (String) request.getParameter("Album_title");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>上传相片</title>
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
	        <form action="UploadServlet" method="post" enctype="multipart/form-data">
	            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择你要上传的相片：<input type="file" name="upfile" /></p><br /> 
	            <p>相片简介：<input type="text" name="note"  id="albumValue"/></p><br />
	            <input type="submit" value="上传" /> 
	            <input type="reset" value="重置" />
	            <input type="hidden"  name="photo_user" value="<%=user_name%>">
	            <input type="hidden"  name="Album_title" value="<%=Album_title%>">
	        </form>
	        <a href="home.jsp" id="backindex">返回主页</a>
	       </div>
    </body>
</html>
