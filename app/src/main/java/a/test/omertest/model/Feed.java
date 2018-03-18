package a.test.omertest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class Feed {

    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }
}