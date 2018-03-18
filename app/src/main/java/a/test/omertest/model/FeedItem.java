package a.test.omertest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem {

    @Element(name = "title")
    private String title;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "guid")
    private String guid;

    @Element(name = "link")
    private String link;

    @Element(name = "description")
    private String description;

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

    public String getGuid() {
        return guid;
    }

}
