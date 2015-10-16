package servlet;

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

@WebServlet(urlPatterns = {"/DaoServlet"})
public class DaoServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        System.out.println("----DaoServlet启动----");
        String album_name = req.getParameter("album_name");
        String isLogin = req.getParameter("isLogin");
        //System.out.println(album_name);
        session.setAttribute("user_name", album_name);
        //System.out.println(user_name + isLogin);
        DAO dao = new DAO();
        List album_List;
        List album_List_sort;
        List photo_list;
        List photo_list_sort;
        //Album album_list;
        try {
            //System.out.println("1");
            album_List = dao.album_List(album_name, isLogin);
            session.setAttribute("album_List", album_List);
            System.out.println("DaoServlet，album_List：成功！");

            album_List_sort = album_List;
            album_List_sort = dao.album_List_sort(album_List_sort);
            session.setAttribute("album_List_sort", album_List_sort);
            System.out.println("DaoServlet，album_List_sort：成功！");
            photo_list = (List)dao.get_all_photo(isLogin, album_name);
            session.setAttribute("photo_List", photo_list);
            
            photo_list_sort = dao.photo_List_sort(photo_list);
            session.setAttribute("photo_List_sort", photo_list_sort);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("DaoServlet，doPost：异常！");
            Logger.getLogger(DaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //req.getRequestDispatcher("home.jsp").include(req, resp);
        resp.sendRedirect("home.jsp");
        System.out.println("----DaoServlet结束----");
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
