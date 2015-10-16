package dao;

import bean.Album;
import bean.Photo;
import bean.User;
import getConnection.GetConnection;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    //private final String driver = "com.mysql.jdbc.Driver";
    //private final String url = "jdbc:mysql://localhost:3306/job10";
    private Connection conn;
    private PreparedStatement pstat;
    public String sql = null;

 
    public boolean login(User user) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "select * from user where user_name=? and user_password=? ";

        pstat = conn.prepareStatement(sql);

        pstat.setString(1, user.getUser_name());
        pstat.setString(2, user.getUser_password());

        try (ResultSet rs = (ResultSet) pstat.executeQuery()) {
            i = rs.next();
        }
        pstat.close();
        conn.close();
        System.out.println("验证成功");
        return i;
    }

    public boolean create_user(User user) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "insert into user values (?, ?)";

        pstat = conn.prepareStatement(sql);

        pstat.setString(1, user.getUser_name());
        //System.out.println(album.getAlbum_title() + "123123123");
        pstat.setString(2, user.getUser_password());

        i = pstat.executeUpdate() > 0;

        pstat.close();
        conn.close();
        System.out.println("DAO,create_user:执行成功！");
        return i;
    }

    public boolean create_album(Album album) throws SQLException {
        conn = GetConnection.getConnection();
        System.out.println(album.getAlbum_title());
        boolean i;
        sql = "insert into album values (?, ?, ?, ?, ?)";

        pstat = conn.prepareStatement(sql);
        //19:20修改
        /*
        if(album.getAlbum_title()==null||album.getAlbum_isopen()==null)
        {
            
        }
        */
        pstat.setString(1, album.getAlbum_title());
        //System.out.println(album.getAlbum_title() + "123123123");
        pstat.setString(2, album.getAlbum_time());
        pstat.setString(3, album.getAlbum_isopen());
        pstat.setString(4, album.getAlbum_number());
        pstat.setString(5, album.getAlbum_user());
 

        i = pstat.executeUpdate() > 0;

        pstat.close();
        conn.close();
        System.out.println("DAO,create_album:执行成功！");
        return i;
    }

    public boolean delete_album(String album) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "delete from album where album_title='"+album+"'";
        

        pstat = conn.prepareStatement(sql);

        /*
        pstat.setString(1, album.getAlbum_title());
        //System.out.println(album.getAlbum_title() + "123123123");
        pstat.setString(2, album.getAlbum_time());
        pstat.setString(3, album.getAlbum_isopen());
        pstat.setString(4, album.getAlbum_number());
        pstat.setString(5, album.getAlbum_user());
        */
        i = pstat.executeUpdate() > 0;
        
        boolean j;
        sql="delete from photo where photo_album_title='"+album+"'";
        pstat = conn.prepareStatement(sql);
        j = pstat.executeUpdate() > 0;
        pstat.close();
        conn.close();
        System.out.println("DAO,delete_album:执行成功！");
        return (i||j);
    }
     public List get_all_photo(String isLogin, String userName) throws SQLException, ClassNotFoundException {
        List photo_list;
        photo_list = new ArrayList();
        conn = GetConnection.getConnection();
        ResultSet rs = null;
        if (isLogin.equals("false") ) {
                sql = "select * from photo where photo_album_title in (select album_title from album where album_isopen = 'Y')";

                pstat = conn.prepareStatement(sql);
                rs = pstat.executeQuery();
            } else if (isLogin.equals("true")) {
                sql = "select * from photo where photo_user = ? union select * from photo where photo_album_title in (select album_title from album where album_isopen = 'Y')";
                
                pstat = conn.prepareStatement(sql);
                pstat.setString(1, userName);
                rs = pstat.executeQuery();
            }
       
        while (rs.next()) {
            Photo photo = new Photo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
            photo_list.add(photo);
        }

        pstat.close();
        conn.close();
        System.out.println("DAO,List photo_List:执行成功！");
        return photo_list;
    }
        public List photo_List_sort(List<Photo> photo_List_sort) {
        Collections.sort(photo_List_sort, new Comparator() {
            @Override
            public int compare(Object o2, Object o1) {
                Photo p1 = (Photo) o1;
                Photo p2 = (Photo) o2;
                //先根据age进行排序，然后在根据name排序。  
                int flag = p1.getPhoto_number().compareTo(p2.getPhoto_number());
                return flag;
            }
        });
        
        return photo_List_sort;
    }

    public boolean delete_photo(String photo) throws SQLException {
        conn = GetConnection.getConnection();        
        boolean j;
        String tmp = photo.replace("\\", "\\\\");
        System.out.println(tmp);
        sql="delete from photo where photo_position='"+tmp+"'";
        System.out.println(sql);
        pstat = conn.prepareStatement(sql);
        j = pstat.executeUpdate() > 0;
        pstat.close();
        conn.close();
        System.out.println("DAO,delete_photo:执行成功！");
        return j;
    }
    public List search_album(String album_s) throws SQLException, ClassNotFoundException{
        conn = GetConnection.getConnection();
        //sql = "select * from album where album_title='"+album_s+"'";
        sql ="select * from album where album_title like '%"+album_s+"%'";

        System.out.println(sql);
        //sql = "select * from album where album_title="+album_s;
        pstat = conn.prepareStatement(sql);
        /*
        pstat.setString(1, album.getAlbum_title());
        //System.out.println(album.getAlbum_title() + "123123123");
        pstat.setString(2, album.getAlbum_time());
        pstat.setString(3, album.getAlbum_isopen());
        pstat.setString(4, album.getAlbum_number());
        pstat.setString(5, album.getAlbum_user());
        */
        List<Album> album_sr=new ArrayList<>();
        ResultSet rs = pstat.executeQuery();
        while (rs.next()) {
               Album album_s_r=new Album();
               album_s_r.setAlbum_title(rs.getString("album_title"));
               album_s_r.setAlbum_isopen(rs.getString("album_isopen"));
               album_s_r.setAlbum_number(rs.getString("album_number"));
               album_s_r.setAlbum_time(rs.getString("album_time"));
               album_s_r.setAlbum_user(rs.getString("album_user"));
               album_sr.add(album_s_r);
                         
        }
        System.out.println(album_sr.size());
        pstat.close();
        conn.close();
        return album_sr;

    }
    public List album_List(String album_name, String isLogin) throws SQLException, ClassNotFoundException {
        List album_List;
        album_List = new ArrayList();
        //System.out.println("111"+album_name);
        conn = GetConnection.getConnection();
        sql = "select * from album";
        //System.out.println("1");

        pstat = conn.prepareStatement(sql);
        ResultSet rs = pstat.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(5));
            if (isLogin.equals("false") && rs.getString(3).equals("Y")) {
                Album album = new Album(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                //System.out.println(album.getAlbum_title());
                album_List.add(album);System.out.println("1");
            } else if (isLogin.equals("true") && rs.getString(5).equals(album_name) || rs.getString(3).equals("Y")) {
                Album album = new Album(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                //System.out.println(album.getAlbum_title());
                album_List.add(album);System.out.println("11");
            }
        System.out.println("2");
        }

        pstat.close();
        conn.close();
        System.out.println("DAO,List album_List:执行成功！");
        return album_List;
    }

    public List album_List_sort(List<Album> album_List_sort) {
        //Album album;
        //list = new ArrayList<Album>();
        //list.add(new Album("1","1","1" ,"1","1name"));
        //System.out.println("1");
        Collections.sort(album_List_sort, new Comparator() {
            @Override
            public int compare(Object o2, Object o1) {
                Album p1 = (Album) o1;
                Album p2 = (Album) o2;
                //先根据age进行排序，然后在根据name排序。  
                int flag = p1.getAlbum_number().compareTo(p2.getAlbum_number());
                return flag;
            }
        });
        //System.out.println("1");
        //System.out.println("1");
        /*
        System.out.println("1111");
        Collections.sort(album_List_sort, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Album p1 = (Album) o1;
                Album p2 = (Album) o2;
                //根据Album_number进行排序。  
                System.out.println(p1.getAlbum_number() + p2.getAlbum_number());
                int flag = p1.getAlbum_number().compareTo(p2.getAlbum_number());
                System.out.println(flag);
                return flag;
            }
        });
        System.out.println("222");
                */
        return album_List_sort;
    }

    public boolean create_photo(Photo photo) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "insert into photo values (?, ?, ?, ?, ?,?)";

        pstat = conn.prepareStatement(sql);

        pstat.setString(1, photo.getPhoto_bi());
        pstat.setString(2, photo.getPhoto_time());
        pstat.setString(3, photo.getPhoto_number());
        pstat.setString(4, photo.getPhoto_position());
        pstat.setString(5, photo.getPhoto_user());
        pstat.setString(6, photo.getPhoto_album_title());
        //System.out.println("1231232131" + photo.getPhoto_album_title());

        i = pstat.executeUpdate() > 0;

        pstat.close();
        conn.close();
        System.out.println("DAO,create_photo:执行成功！");
        return i;
    }

    public List photo_List(Album album) throws SQLException, ClassNotFoundException {
        List photo_List;
        photo_List = new ArrayList();
        //System.out.println(Album_number);
        //System.out.println(Album_user + Album_title);
        conn = GetConnection.getConnection();
        sql = "select * from photo where photo_user=?  and photo_album_title=? ";

        pstat = conn.prepareStatement(sql);
        pstat.setString(1, album.getAlbum_user());
        pstat.setString(2, album.getAlbum_title());

        //System.out.println("11111" + Album_title);
        ResultSet rs = pstat.executeQuery();
        while (rs.next()) {
            Photo photo = new Photo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            //System.out.println(album.getAlbum_title());
            photo_List.add(photo);
        }

        System.out.println("DAO,List photo_List1:执行成功！");

        sql = "update album set album_number = ? where album_title=? ";
        pstat = conn.prepareStatement(sql);
        pstat.setString(1, album.getAlbum_number());
        pstat.setString(2, album.getAlbum_title());
        //System.out.println("22222" + album.getAlbum_title());
        int executeUpdate = pstat.executeUpdate();
        pstat.close();
        conn.close();

        System.out.println("DAO,List photo_List2:执行成功！");
        return photo_List;
    }

    public boolean setIsopen(Album album,String title_new) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "update album set album_isopen = ? where album_title = ? ";

        pstat = conn.prepareStatement(sql);
        
        pstat.setString(1, album.getAlbum_isopen());
        pstat.setString(2, album.getAlbum_title());
        //System.out.println("1231232131" + photo.getPhoto_album_title());
        //
        System.out.println(sql);
        System.out.println(album.getAlbum_title());
        i = pstat.executeUpdate() > 0;
        //
        sql = "update album set album_title = '"+title_new+"' where album_title =?";
        pstat = conn.prepareStatement(sql);
        pstat.setString(1, album.getAlbum_title());
        //
        System.out.println(sql);
        System.out.println(album.getAlbum_title());
        boolean j;
        j = pstat.executeUpdate() > 0;
        //
        sql = "update photo set photo_album_title = '"+title_new+"' where photo_album_title =?";
        pstat = conn.prepareStatement(sql);
        pstat.setString(1, album.getAlbum_title());
        //
        System.out.println(sql);
        System.out.println(album.getAlbum_title());
        boolean z;
        z = pstat.executeUpdate() > 0;
        pstat.close();
        conn.close();
        System.out.println("DAO,setIsopen:执行成功！");
        return (i||j||z);
    }

    public boolean update_photo(String photo_bi, String photo_number) throws SQLException {
        conn = GetConnection.getConnection();
        boolean i;
        sql = "update photo set photo_number = ? where photo_bi = ? ";
        pstat = conn.prepareStatement(sql);
        pstat.setString(1, photo_number);
        pstat.setString(2, photo_bi);
        i = pstat.executeUpdate() > 0;
        pstat.close();
        conn.close();

        System.out.println("DAO,update_photo:执行成功！");
        return i;
    }

}
