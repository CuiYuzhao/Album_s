package servlet;

import bean.Album;
import dao.DAO;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/IsopenServlet"})
public class IsopenServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----SetIsopenServlet启动----");

        HttpSession session = req.getSession();
        String album_title = req.getParameter("Album_title");
        String album_title_new=req.getParameter("album_title");
        System.out.println(album_title_new+"-----");
        String album_isopen = req.getParameter("album_isopen");
        String album_user = req.getParameter("Album_user");
        //System.out.println("sps" + album_title);
        DAO dao = new DAO();
        Album album = new Album();
        album.setAlbum_user(album_user);
        album.setAlbum_isopen(album_isopen);
        album.setAlbum_time("");
        album.setAlbum_title(URLEncoder.encode(album_title, "UTF-8"));
        album.setAlbum_number("");
        boolean i;
        try {
            i = dao.setIsopen(album,album_title_new);//album_title, album_isopen
            //album_title = URLEncoder.encode(album_title, "UTF-8");
            if(album_title_new==null){
                
            
            String url = "showphoto.jsp?Album_user=" + album.getAlbum_user()
                    + "&Album_title=" + album_title
                    + "&Album_isopen=" + album.getAlbum_isopen();
            resp.sendRedirect(url);
            }
            else{
                String url = "showphoto.jsp?Album_user=" + album.getAlbum_user()
                    + "&Album_title=" + album_title_new
                    + "&Album_isopen=" + album.getAlbum_isopen();
                resp.sendRedirect(url);
            }
            
            //resp.setHeader("Refresh", "0;URL=game.jsp?g=0");
            //resp.setHeader("Refresh", "showphoto.jsp?Album_user=" + album_user + "&Album_title=" + album_title);
            session.setAttribute("isopen", album_isopen);
            //session.setAttribute("album_title", album_title);
            //System.out.println(album.getAlbum_title());
        } catch (SQLException ex) {
            System.out.println("SetIsopenServlet，doPost：异常！");
            Logger.getLogger(ShowPhotoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("----SetIsopenServlet结束----");
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
