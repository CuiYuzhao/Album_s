package bean;

public class Album {

    private String album_title;
    private String album_time;
    private String album_isopen;
    private String album_number;
    private String album_user;

    public Album() {
        this.album_title = "";
        this.album_time = "";
        this.album_isopen = "";
        this.album_number = "";
        this.album_user = "";
    }

    public Album(String album_title, String album_time, String album_isopen, String album_number, String album_user) {
        this.album_title = album_title;
        this.album_time = album_time;
        this.album_isopen = album_isopen;
        this.album_number = album_number;
        this.album_user = album_user;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getAlbum_time() {
        return album_time;
    }

    public void setAlbum_time(String album_time) {
        this.album_time = album_time;
    }

    public String getAlbum_isopen() {
        return album_isopen;
    }

    public void setAlbum_isopen(String album_isopen) {
        this.album_isopen = album_isopen;
    }

    public String getAlbum_number() {
        return album_number;
    }

    public void setAlbum_number(String album_number) {
        this.album_number = album_number;
    }

    public String getAlbum_user() {
        return album_user;
    }

    public void setAlbum_user(String album_user) {
        this.album_user = album_user;
    }

}
