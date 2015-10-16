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
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");//解决<jsp:param name="" value=""/> value中文乱码问题
            //response.setContentType("text/html");
            //response.setCharacterEncoding("UTF-8");
        %>
        <jsp:include page='<%="/ShowPhotoServlet"%>'>
            <jsp:param name="Album_user" value="<%=Album_user%>"/>
            <jsp:param name="Album_number" value="<%=hitsCount%>"/>
            <jsp:param name="Album_title" value="<%=Album_title%>"/>
        </jsp:include>
        <h2>亲爱的：【<%=user_name%>】，您当前访问的是【<%=Album_user%>】的【<%=Album_title%>】相册</h2>
        <a href="home.jsp">相册主页</a>
        <%
            if (root.equals("Y") && user_name.equals(Album_user)) {
        %>
        <a href="upload.jsp?Album_user=<%=Album_user%>&Album_title=<%=Album_title%>">上传相片</a>   
        <form name="set" action="IsopenServlet" method="post">
            是否开放浏览：<input type="radio" name="album_isopen" value="Y" />是<input type="radio" name="album_isopen" value="N" />否</br>
            修改相册名称：<input type="text" name="album_title" value="<%=Album_title%>"/></br>
            <input type="submit" value="设置" />
            <input type="hidden"  name="Album_title" value="<%=Album_title%>">
            <input type="hidden"  name="Album_user" value="<%=Album_user%>">
        </form>
        <p>当前权限为：<%=isopen%></p>
        <%
            } else {
                out.print("<br /><br />");
            }
        %>

        <%
            List list_1 = (List) session.getAttribute("photo_List");
            Photo photo_List;
            for (int i = 0; i < list_1.size(); i++) {
                photo_List = (Photo) list_1.get(i);
        %>
        <div style="float: left;margin-left:20px;margin-top:20px;">
            <div>
                <a href="show.jsp?url=<%=photo_List.getPhoto_position()%>&photo_bi=<%=photo_List.getPhoto_bi()%>"><%="<img src=\"" + photo_List.getPhoto_position() + "\" height=\"240px\" width=\"240px\"></img>"%></a>
            </div>
            <div>
                <center>简介：<%=photo_List.getPhoto_bi()%> 访问次数：<%=photo_List.getPhoto_number()%></center>
            </div>
            <div>
                <center>上传时间：<%=photo_List.getPhoto_time()%></center>
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
            <input type="submit" value="删除" />
            </form>
                <% } %>
        </div>
        <%
            }
        %>
        
    </body>
</html>
