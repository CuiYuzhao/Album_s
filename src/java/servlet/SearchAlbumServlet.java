package servlet;

import bean.Album;
import dao.DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/SearchAlbumServlet"})
public class SearchAlbumServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----SearchAlbumServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        //HttpSession session = req.getSession(); 
        HttpSession session = req.getSession();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String album_time = dateFormat.format(now);
        String album_title_s = req.getParameter("album_title_s");
        //String album_isopen = req.getParameter("album_isopen");
        //String album_user = req.getParameter("album_name");
        //System.out.println(album_user);

        String album_number = "0";
        /*
        Album album = new Album();
        album.setAlbum_title(album_title);
        album.setAlbum_time(album_time);
        album.setAlbum_isopen(album_isopen);
        album.setAlbum_number(album_number);
        album.setAlbum_user(album_user);
        session.setAttribute("user_name", album_user);
        session.setAttribute("Album_title_s", album_title);
        session.setAttribute("Album_isopen", album_isopen);
                */
        DAO dao = new DAO();
        List<Album> album_sr=new ArrayList<>();
        try {
            album_sr=dao.search_album(album_title_s);
            session.setAttribute("list", album_sr);
            System.out.println(album_sr.toString());
            resp.sendRedirect("search_album_s.jsp");
            /*
            try {
            isCreate = dao.create_album(album);
            if (isCreate) {
            resp.sendRedirect("search_album_s.jsp");
            //session.setAttribute("user_name", user_name);
            } else {
            resp.sendRedirect("index.jsp");
            }
            } catch (SQLException e) {
            System.out.println("CreateAlbumServlet,doPost异常：" + e.toString());
            }
            System.out.println("----CreateAlbumServlet结束----");
            */
        } catch (SQLException ex) {
            Logger.getLogger(SearchAlbumServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchAlbumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
