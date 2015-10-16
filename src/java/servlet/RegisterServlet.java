package servlet;

import bean.Album;
import bean.User;
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

@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----RegisterServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        //HttpSession session = req.getSession(); 
        HttpSession session = req.getSession();
        String user_name = req.getParameter("user_name");
        String user_password = req.getParameter("user_password");
        //System.out.println(album_user);

        User user = new User();
        user.setUser_name(user_name);
        user.setUser_password(user_password);
        
        DAO dao = new DAO();
        boolean isCreate;
        try {
            isCreate = dao.create_user(user);
            if (isCreate) {
                resp.sendRedirect("create_user_s.jsp");
                session.setAttribute("user_name", user_name);
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            System.out.println("RegisterServlet,doPost异常：" + e.toString());
        }
        System.out.println("----RegisterServlet结束----");
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
