package servlet;

import bean.Album;
import dao.DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/DeleteAlbumServlet"})
public class DeleteAlbumServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----DeleteAlbumServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        //HttpSession session = req.getSession(); 
        HttpSession session = req.getSession();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String album_time = dateFormat.format(now);
        req.setCharacterEncoding("UTF-8");
        String album_title_d = req.getParameter("album_title_d");
        //String album_isopen = req.getParameter("album_isopen");
        //String album_user = req.getParameter("album_name");
        //System.out.println(album_user);

        String album_number = "0";
        //Album album = new Album();
        //album.setAlbum_title(album_title_d);
        //album.setAlbum_time(album_time);
        //album.setAlbum_isopen(album_isopen);
        //album.setAlbum_number(album_number);
        //album.setAlbum_user(album_user);
        //session.setAttribute("user_name", album_user);
        //session.setAttribute("Album_title", album_title_d);
        //session.setAttribute("Album_isopen", album_isopen);
        DAO dao = new DAO();
        boolean isDelete;
        try {
            isDelete = dao.delete_album(album_title_d);
            if (isDelete) {
                resp.sendRedirect("delete_album_s.jsp");
                //session.setAttribute("user_name", user_name);
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            System.out.println("DeleteAlbumServlet,doPost异常：" + e.toString());
        }
        System.out.println("----DeleteAlbumServlet结束----");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
    }
}
