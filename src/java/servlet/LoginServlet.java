package servlet;

import dao.DAO;
import bean.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        System.out.println("----LoginServlet启动----");
        //PrintWriter out = response.getWriter();
        //String name = new String(request.getParameter("user_name").getBytes("ISO8859_1"), "GBK");
        //String pwd = new String(request.getParameter("user_password").getBytes("ISO8859_1"), "UTF-8");
        HttpSession session = req.getSession();
        String user_name = req.getParameter("user_name");
        String user_password = req.getParameter("user_password");
        User user = new User();
        user.setUser_name(user_name);
        user.setUser_password(user_password);
        DAO dao = new DAO();
        boolean isLogin;
        try {
            isLogin = dao.login(user);
            if (isLogin) {
                resp.sendRedirect("home.jsp");
                session.setAttribute("user_name", user_name);
                //System.out.println(user_name);
            } else {
                resp.sendRedirect("index.jsp");
            }
            session.setAttribute("isLogin", isLogin);
        } catch (SQLException e) {
            System.out.println("LoginServlet,doPost异常：" + e.toString());
        }
        System.out.println("----LoginServlet结束----");
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
