
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bean.Album"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List result=new ArrayList();
    result=(List)session.getAttribute("list");
    
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查询结果</title>
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
        	a{
        		color:white;
        		text-decoration: underline;
        	}
        </style>
    </head>
    <body>
    	<div id="wrap">
	        <h1>符合要求相册列表</h1>
	        
	        <%
	            for(int i = 0 ; i < result.size(); i++) {
	                Album album = (Album)result.get(i);
	                
	                out.println("相册：<a href=\"showphoto.jsp?Album_user=" + album.getAlbum_user()
	                            + "&Album_title=" + album.getAlbum_title()
	                            + "&Album_isopen=" + album.getAlbum_isopen() + "\">"
	                            + album.getAlbum_title()+ "</a>");
	                out.println("创建时间：" + album.getAlbum_time());
	                out.println("历史访问量：" + album.getAlbum_number());
	                out.println("创建者："+album.getAlbum_user());
	                out.println("是否开放：" + album.getAlbum_isopen());
	                out.println("</br>");
	                        
	                
	            }
	        %>
	        <h2><a href="home.jsp">返回主页</a></h2>
        </div>
    </body>
</html>
