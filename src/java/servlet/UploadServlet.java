package servlet;

import bean.Photo;
import dao.DAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    private static final boolean WRITE_TO_FILE = true;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        /*
         req.setCharacterEncoding("UTF-8");    //这个是没有用的
         String v = req.getParameter("note");  //得不到相应的表单域
         System.out.println("-------->" + v);
         */
        // Check that we have a file upload request
        System.out.println("----UploadServlet启动----");
        HttpSession session = req.getSession();
        //String photo_user = req.getParameter("photo_user");
        //System.out.println(photo_user);

        Photo photo = new Photo();
        Date now = new Date();
        String photo_number = "0";
        photo.setPhoto_number(photo_number);
        //photo.setPhoto_user(photo_user);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format(now);
        session.setAttribute("time", time);
        photo.setPhoto_time(time);
        //System.out.println(time);
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        if (isMultipart) {
            //create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //可以用以下代码，Sets the size threshold(阈值) beyond which files are written directly to disk. in bytes
            //factory.setSizeThreshold(4096); Size threshold is 10KB

            //配置一个库，确保临时位置是可用的
            ServletContext ctx = this.getServletConfig().getServletContext();
            File repository = (File) ctx.getAttribute("javax.servlet.context.tempdir");
            System.out.println("---->" + repository.getAbsoluteFile()); //测试用代码

            //创建一个新的文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("utf-8"); //解决中文乱码问题

            // Sets the maximum allowed size of a complete request, 
            // as opposed to setFileSizeMax(long).
            // 整个请求的最大Size
            //upload.setSizeMax(1000);
            try {
                //分析请求
                List<FileItem> items = upload.parseRequest(req);

                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) { //处理普通表单域 
                        String name = item.getFieldName(); //获取表单域名称
                        String value = item.getString("utf-8");   //获取表单域值，指定编码
                        System.out.println("1---->" + name + ", " + value);
                        if (name.equals("note")) {
                            //System.out.println(value);
                            photo.setPhoto_bi(value);
                            session.setAttribute("bi", value);
                            System.out.println(value);
                            

                        } else if (name.equals("photo_user")) {
                            photo.setPhoto_user(value);
                            session.setAttribute("user_name", value);
                            System.out.println(value);

                        } else {
                            photo.setPhoto_album_title(value);
                            session.setAttribute("Album_title", value);
                            System.out.println(value);

                        }
                    } else { //处理上传文件
                        //以下代码可以用于检测状态
                        String fieldName = item.getFieldName();
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();

                        System.out.println("--->FieldName:" + fieldName + ", contentType:" + contentType + ", isInMemory:" + isInMemory + ", size:" + sizeInBytes);

                        //------------------------------------------------------
                        //以下代码没有处理没有上传文件的情况
                        if (WRITE_TO_FILE) { //直接将上传文件保存到服务器
                            String fileName = item.getName(); //获取上传文件名，可以自行改名

                            String root = this.getServletContext().getRealPath("/");
                            System.out.println("2---->" + root);
                            File file = new File(root + "\\upload\\" + fileName);  //准备好文件名
                            session.setAttribute("rurl", root + "\\upload\\" + fileName);
                            session.setAttribute("url", "upload\\" + fileName);
                            photo.setPhoto_position("upload\\" + fileName);

                            item.write(file);  //写入指定的文件
                            //resp.sendRedirect("show.jsp");
                        } else { //直接在内存中处理上传的文件
                            InputStream in = item.getInputStream();
                            int i = 0;
                            while (in.read() != -1) {
                                i++;
                                if (i % (1024 * 1024) == 0) {
                                    System.out.println("3---->processed 1M bytes.");
                                }
                            }
                            System.out.println("4---->processed total " + i + " bytes.");
                            in.close();
                        }
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DAO dao = new DAO();
        boolean isCreate;
        try {
            //System.out.println("3111");
            isCreate = dao.create_photo(photo);
            //System.out.println("3");
            if (isCreate) {
                //System.out.println("1");
                resp.sendRedirect("upload_photo_s.jsp");
                //session.setAttribute("user_name", user_name);
            } else {
                //System.out.println("2");
                resp.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            System.out.println("UploadServlet,doPost异常：" + e.toString());
        }

        System.out.println("----UploadServlet结束----");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
    }

}
