<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>创建相册</title>
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
    <%
        String user_name = (String) session.getAttribute("user_name");
        //out.print(user_name);
    %>
    
    <body>
    	<div id="wrap">
	      
	        <span id="deleteAlbum">创建相册</span>
	        <form name="create" action="CreateAlbumServlet" method="post">
	            <p>相册主题：<input type="text" name="album_title" id="albumValue"/></p>
	            <p>是否开放浏览：
	            	<input type="radio" name="album_isopen" value="Y" />是
	            	<input type="radio" name="album_isopen" value="N" />否
	            </p>
	            <input type="submit" id="submit" value="提交" id="backindex"/>
	            <input type="hidden"  name="album_name" value="<%=user_name%>">
	        </form>
	         <a href="home.jsp" id="backindex">返回主页</a>
        </div>
        <script>
	        function $(id){
	    		return document.getElementById(id);
	    	}
        	document.getElementsByTagName("input")[1].checked = true;
        	$("submit").onclick = function(){
        		if($("title").value == ""){
        			
        		}
        	}
        	
        </script>
    </body>
</html>
