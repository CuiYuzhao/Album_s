package bean;

public class Photo {

    private String photo_bi;
    private String photo_time;
    private String photo_number;
    private String photo_position;
    private String photo_user;
    private String photo_album_title;

    public Photo() {
        this.photo_bi = "";
        this.photo_time = "";
        this.photo_number = "";
        this.photo_position = "";
        this.photo_user = "";
        this.photo_album_title = "";
    }

    public Photo(String photo_bi, String photo_time, String photo_number, String photo_position, String photo_user,String photo_album_title) {
        this.photo_bi = photo_bi;
        this.photo_time = photo_time;
        this.photo_number = photo_number;
        this.photo_position = photo_position;
        this.photo_user = photo_user;
        this.photo_album_title = photo_album_title;
    }

    public String getPhoto_bi() {
        return photo_bi;
    }

    public void setPhoto_bi(String photo_bi) {
        this.photo_bi = photo_bi;
    }

    public String getPhoto_time() {
        return photo_time;
    }

    public void setPhoto_time(String photo_time) {
        this.photo_time = photo_time;
    }

    public String getPhoto_number() {
        return photo_number;
    }

    public void setPhoto_number(String photo_number) {
        this.photo_number = photo_number;
    }

    public String getPhoto_position() {
        return photo_position;
    }

    public void setPhoto_position(String photo_position) {
        this.photo_position = photo_position;
    }

    public String getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public String getPhoto_album_title() {
        return photo_album_title;
    }

    public void setPhoto_album_title(String photo_album_title) {
        this.photo_album_title = photo_album_title;
    }

}
