package servlet;

import bean.Album;
import dao.DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ShowPhotoServlet"})
public class ShowPhotoServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----ShowPhotoServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        //HttpSession session = req.getSession();
        HttpSession session = req.getSession();
        String Album_user = req.getParameter("Album_user");
        String Album_title = req.getParameter("Album_title");
        String Album_number = req.getParameter("Album_number");
        //System.out.println("1" + Album_title + "SPS");
        //System.out.println("sps" + Album_title);
        DAO dao = new DAO();
        Album album = new Album();
        album.setAlbum_isopen("");
        album.setAlbum_number(Album_number);
        album.setAlbum_time("");
        album.setAlbum_title(Album_title);
        album.setAlbum_user(Album_user);
        List photo_List;
        try {
            photo_List = dao.photo_List(album);
            session.setAttribute("photo_List", photo_List);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("ShowPhotoServlet，doPost：异常！");
            Logger.getLogger(ShowPhotoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("----ShowPhotoServlet结束----");
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
