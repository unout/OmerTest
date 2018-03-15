package a.test.omertest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import io.realm.RealmObject;

@Root(name = "item", strict = false)
public class FeedItem extends RealmObject {
    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "title")
    private String title;

    @Element(name = "guid")
    private String guid;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;
   // TODO
//    @Element(name = "img")
//    private String img;

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
