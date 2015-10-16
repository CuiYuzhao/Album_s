<%@page import="bean.Photo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String isopen = (String) request.getParameter("Album_isopen");
    String root = (String) session.getAttribute("root");
    String user_name = (String) session.getAttribute("user_name");
    String Album_user = (String) request.getParameter("Album_user");
    String Album_title = (String) request.getParameter("Album_title");

    String Album_number = Album_title + "_number";
    Integer hitsCount = (Integer) application.getAttribute(Album_number);
    if (hitsCount == null || hitsCount == 0) {
        /* 第一次访问 */
        hitsCount = 1;
    } else {
        /* 返回访问值 */
        hitsCount += 1;
    }
    application.setAttribute(Album_number, hitsCount);

    if (user_name.equals("null") || user_name.equals("")) {
        user_name = "游客";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=Album_title%></title>
        <link rel="stylesheet" href="css/showphoto.css">
        <link rel="stylesheet" href="images/image-hover.css" type="text/css">
		<link rel="stylesheet" href="images/image-hover-main.css" type="text/css">
		<script src="images/browserCheck.js" type="text/javascript"></script>
        <style>
        	body{
				background: url(img/1.jpg);
				color: white;
			}
			#wrap{
				width:100%;
				
			}
			.photo{
				float: left;
				width: 162px;
				margin-right:50px;
			}
			.album{
				width: 1000px;
				margin: auto;
				margin-bottom:30px;
			}
			#head{
				width:550px;
				margin:auto;
				margin-bottom: 30px;;
				text-align: center;
			}
			.chars{
				color: white;
				line-height: 40px;
				text-decoration: underline;
			}
			.form_line{
				display:block;
				height: 30px;
			}
			#input_context{
				outline:none;
				width:150p;
				border-radius: 5px;
				padding-left:5px;
			}
			.delete{
				display: block;
				margin: auto;
				cursor: pointer;
			}
        </style>
    </head>
    <body>
    	<div id="wrap">
    		<div id="head">
		        <%
		            request.setCharacterEncoding("UTF-8");//解决<jsp:param name="" value=""/> value中文乱码问题
		        %>
		        <jsp:include page='<%="/ShowPhotoServlet"%>'>
		            <jsp:param name="Album_user" value="<%=Album_user%>"/>
		            <jsp:param name="Album_number" value="<%=hitsCount%>"/>
		            <jsp:param name="Album_title" value="<%=Album_title%>"/>
		        </jsp:include>
                        </br></br></br></br></br></br></br></br></br></br>
                        
		        <h2>Hello【<%=user_name%>】您当前访问的是【<%=Album_title%>】相册</h2>
		        <a href="home.jsp" class="chars">相册主页</a>
		        <%
		            if (root.equals("Y") && user_name.equals(Album_user)) {
		        %>
		        <a href="upload.jsp?Album_user=<%=Album_user%>&Album_title=<%=Album_title%>" class="chars" style="color:white;text-decoration:underline;">上传相片</a>   
		        <form name="set" action="IsopenServlet" method="post">
		            <span class="form_line">是否开放浏览：<input type="radio" name="album_isopen" value="Y" checked="true"/>是<input type="radio" name="album_isopen" value="N" />否</span>
		            <span class="form_line">修改相册名称：<input type="text" name="album_title" value="<%=Album_title%>" id="input_context"/></span>
		            <input type="submit" value="修改" />
		            <input type="hidden"  name="Album_title" value="<%=Album_title%>">
		            <input type="hidden"  name="Album_user" value="<%=Album_user%>">
		        </form>
		        <p>当前权限为：<%=isopen%></p>
		     </div>
        <%
            } else {
                out.print("<br />");
            }
        %>
		<div class="album">
	        <%
	            List list_1 = (List) session.getAttribute("photo_List");
	            Photo photo_List;
	            for (int i = 0; i < list_1.size(); i++) {
	                photo_List = (Photo) list_1.get(i);
	        %>
	        <div class="photo">
	            <div>
	                <a href="show.jsp?url=<%=photo_List.getPhoto_position()%>&photo_bi=<%=photo_List.getPhoto_bi()%>">
	                	
	                	<div class="img" id="img-1">
							<div class="mask"></div>
							<%="<img src=\"" + photo_List.getPhoto_position() + "\" height=\"240px\" width=\"240px\"></img>"%>
						</div>	
	                </a>
	            	
	            </div>
	            <div style="text-align:center;margin-bottom:5px;">
	              <center>简介：<%=photo_List.getPhoto_bi()%></center>
	            </div>
	            <div  style="textalign:center;">
	                上传时间：<%=photo_List.getPhoto_time()%>
	            </div>
                     <%
		            if (root.equals("Y") && user_name.equals(Album_user)) {
		        %>
	                <form name="<%=i%>" action="DeletePhotoServlet" method="post" >
	                    <input type="hidden" name="image" value ="<%=i%>">
	                <%
	                String tmp = "photo_position";
	                session.setAttribute(tmp+i,photo_List.getPhoto_position());
	                %>
		            <input type="submit" value="删除" class="delete"/>
		            </form>
                        <% } %>
	        </div>
	        <%
	            }
	        %>
	    </div>
     </div>
    </body>
</html>
