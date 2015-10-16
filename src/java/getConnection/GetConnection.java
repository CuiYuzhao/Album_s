package getConnection;

import java.sql.*;

public class GetConnection {

    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/album_s";
        Connection conn = null;
        try {
            Class.forName(driver);
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/album_s?user=root&password=123456&useUnicode=true&characterEncoding=GBK");
            } catch (SQLException e) {
                System.out.println("conn异常:" + e.toString());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class.forName异常:" + e.toString());
        }
        System.out.println("成功连接数据库！");
        return conn;
    }
    /*public static void main(String args[]){
     getConnection();
     }*/
}
