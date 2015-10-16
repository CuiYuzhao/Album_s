package servlet;

import bean.Album;
import dao.DAO;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/DeletePhotoServlet"})
public class DeletePhotoServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----DeletephotoServlet启动----");

        HttpSession session = req.getSession();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String album_time = dateFormat.format(now);
        req.setCharacterEncoding("UTF-8");
        String tmp = req.getParameter("image");
        String photo_position_d = (String) session.getAttribute("photo_position"+tmp);
        System.out.println(photo_position_d+":"+"photo_position"+tmp);
        
        String album_number = "0";

        DAO dao = new DAO();
        boolean isDelete;
        try {
            isDelete = dao.delete_photo(photo_position_d);
            if (isDelete) {
                        
                        resp.sendRedirect("home.jsp?delete=y");
 
            } else {
                resp.sendRedirect("home.jsp");
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

