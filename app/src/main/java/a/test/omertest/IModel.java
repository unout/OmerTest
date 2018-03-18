package a.test.omertest;

import java.util.List;

import a.test.omertest.model.Feed;
import a.test.omertest.model.RoomItem;
import retrofit2.Response;

public interface IModel {

    List<RoomItem> getItems();

    void saveFeed(Response<Feed> feedResponse);

    boolean isOnline();
}