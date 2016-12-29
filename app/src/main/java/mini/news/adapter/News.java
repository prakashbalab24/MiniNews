package mini.news.adapter;

/**
 * Created by prakash-bala on 14/12/16.
 */

public class News {
    private String title;
    private String thumbnail;

    public News() {
    }

    public News(String title,String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}