<%@page import="bean.Album"%>
<%@page import="bean.Photo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>欢迎来到相册图片管理系统主页</title>

        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/home.css">
    </head>
    <%
        String user_name = "";
        boolean isLogin = false;
        try {
            user_name = (String) session.getAttribute("user_name");
            isLogin = (boolean) session.getAttribute("isLogin");
        } catch (Exception e) {
        }
        //out.print(user_name);
    %>
    <body>
        <div id="wrap">
            <div id="welcome">
            	
	                <%
	                    if (isLogin) {
	                        session.setAttribute("root", "Y");
	                        
	                        out.print("<h1>Hello 【" + user_name + "】  欢迎您来到相册图片管理系统主页</h1>");
	                        out.print("<div id=\"nav\">"); 
	                        out.print("<a href=\"logout.jsp\">用户注销</a>&nbsp;&nbsp;&nbsp;");
	                        out.print("<a href=\"create_album.jsp\">创建相册</a>&nbsp;&nbsp;&nbsp;");
	                        out.print("<a href=\"delete_album.jsp\">删除相册</a>&nbsp;&nbsp;&nbsp;");
	                        out.print("<a href=\"search_album.jsp\">查找相册</a>&nbsp;&nbsp;&nbsp;</br>");
	                        out.print("</div>");
							out.print("</br>");
	                        
	                    } else {
	                        session.setAttribute("root", "N");
	                        out.print("<h1>Hello，欢迎您来到图片相册管理系统主页</h1>");
	                        out.print("<a style=\"display:block;margin:auto;\" href=\"index.jsp\">登陆</a></br>");
                                
                                out.print("<a style=\"display:block;margin:auto;\" href=\"search_album.jsp\">搜索</a>");
                                
	                    }
	                
	                %>
               
                <jsp:include page='<%="/DaoServlet"%>'>
                    <jsp:param name="album_name" value="<%=user_name%>"/>
                    <jsp:param name="isLogin" value="<%=isLogin%>"/>
                </jsp:include>
                <%
                    List list = (List) session.getAttribute("album_List");
                    Album album_List;

                    for (int i = 0; i < list.size(); i++) {
                        album_List = (Album) list.get(i);
						out.print("<div class=\"album\">");
                        out.println("相册：<a href=\"showphoto.jsp?Album_user=" + album_List.getAlbum_user()
                                + "&Album_title=" + album_List.getAlbum_title()
                                + "&Album_isopen=" + album_List.getAlbum_isopen() + "\">"
                                + album_List.getAlbum_title() + "</a>");

                        //out.println(album_List.getAlbum_title());
                        out.println("创建时间：" + album_List.getAlbum_time());
                        out.print("<br/>");
                        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.println("历史访问量：" + album_List.getAlbum_number());
                        out.println("创建者：" + album_List.getAlbum_user());
                        if (user_name.equals(album_List.getAlbum_user())) {
                            out.println("是否开放：" + album_List.getAlbum_isopen());
                        }        
                        out.print("</div>");
                        out.print("<br /><br />");
                   	 	}
                %>
            </div>

			<div id="rank">
	            <div>
	                <h2>最热相册排行榜 TOP 10</h2>
	                <%
	                    List List = (List) session.getAttribute("album_List_sort");
	                    Album album_List_sort;
	                    for (int i = 0, j = 1; i <= 10; i++, j++) {
	                        out.print("TOP " + j + "：");
	                        if (i < list.size()) {
	                            album_List_sort = (Album) List.get(i);
	
	                            out.println("<a href=\"showphoto.jsp?Album_user=" + album_List_sort.getAlbum_user()
	                                    + "&Album_title=" + album_List_sort.getAlbum_title()
	                                    + "&Album_isopen=" + album_List_sort.getAlbum_isopen() + "\">"
	                                    + album_List_sort.getAlbum_title() + "</a>");
	
	                            //out.println(album_List.getAlbum_title());
	                            out.print("<div class=\"vc-count\">"); 
	                            out.println("访问量：" + album_List_sort.getAlbum_number());
	                            out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	                            out.println("创建者：" + album_List_sort.getAlbum_user());
	                            out.print("</div>");
	                        }
	                        out.print("<br />");
	                    }
	                %>
	            </div>
	            <div>
            <h2>最热相片排行榜 TOP10</h2>
            <%
                List photoList = (List) session.getAttribute("photo_List_sort");
                Photo photo_List_sort;
                for (int i = 0, j = 1; i <= 10; i++, j++) {
                    out.print("TOP " + j + "：");
                    if (i < photoList.size()) {
                        photo_List_sort = (Photo) photoList.get(i);  
                        
                      
                        out.println("<a href=\"show.jsp?url= " + photo_List_sort.getPhoto_position()
                                + "&photo_bi=" + photo_List_sort.getPhoto_bi()
                                + "&Photo_Position=" + photo_List_sort.getPhoto_position() + "\">"
                                + photo_List_sort.getPhoto_bi() + "</a>");

                        //out.println(album_List.getAlbum_title());
                        out.print("<div class=\"vc-count\">");                  
                        	out.println("访问量：" + photo_List_sort.getPhoto_number());
                        	out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        	out.println("创建者：" + photo_List_sort.getPhoto_user());
                        out.print("</div>");
                    }
                    out.print("<br />");
                }
            %>
        </div>
        	</div>
        </div>
        <script>
        	document.getElementById("wrap").style.height = document.body.clientHeight;
        </script>
    </body>
</html>