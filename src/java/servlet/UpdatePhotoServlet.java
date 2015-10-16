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

@WebServlet(urlPatterns = {"/UpdatePhotoServlet"})
public class UpdatePhotoServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----ShowPhotoServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        //HttpSession session = req.getSession();
        //HttpSession session = req.getSession();
        String photo_bi = req.getParameter("photo_bi");
        String photo_number = req.getParameter("photo_number");
        String url = req.getParameter("url");
        //System.out.println(photo_bi + photo_number);
        //System.out.println("sps" + Album_title);
        DAO dao = new DAO();
        boolean isUpdate;
        try {
            isUpdate = dao.update_photo(photo_bi, photo_number);
            if (isUpdate) {
                resp.sendRedirect("show.jsp?url=" + url + "&photo_bi=" + photo_bi);
                //session.setAttribute("user_name", user_name);
                //System.out.println(user_name);
            } else {
                resp.sendRedirect("show.jsp");
            }
            //session.setAttribute("isLogin", isLogin);
        } catch (SQLException e) {
            System.out.println("LoginServlet,doPost异常：" + e.toString());
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
