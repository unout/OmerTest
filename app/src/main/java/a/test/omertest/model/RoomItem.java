package a.test.omertest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "omertestdb")
public class RoomItem {

    public int getId() {
        return id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "pubDate")
    private String pubDate;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "description")
    private String description;


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomItem(FeedItem item) {
        this.title = item.getTitle();
        this.pubDate = item.getPubDate();
        this.link = item.getLink();
        this.description = item.getDescription();
    }

    public RoomItem(int id, String title, String pubDate, String link, String description) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
