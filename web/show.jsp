<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>图片</title>
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
    <%
        String url = (String) request.getParameter("url");
        String photo_bi = (String) request.getParameter("photo_bi");
        String photo_number = photo_bi + "_number";
        Integer hitsCount = (Integer) application.getAttribute(photo_number);
        if (hitsCount == null || hitsCount == 0) {
            /* 第一次访问 */
            //out.println("Welcome to my website!");
            hitsCount = 1;
        } else {
            /* 返回访问值 */
            //out.println("Welcome back to my website!");
            hitsCount += 1;
        }
        application.setAttribute(photo_number, hitsCount);
        //out.print(hitsCount);
    %>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");//解决<jsp:param name="" value=""/> value中文乱码问题
        %>
        <jsp:include page='<%="/UpdatePhotoServlet"%>'>
            <jsp:param name="photo_bi" value="<%=photo_bi%>"/>
            <jsp:param name="photo_number" value="<%=hitsCount%>"/>
            <jsp:param name="url" value="<%=url%>"/>
        </jsp:include>
        <%
            out.print("<center><a href=\"javascript:history.back(-1)\"><img src=\"" + url + "\" height=\"560px\" width=\"750px\"></img></a></center>");
        %>
    </body>
</html>
