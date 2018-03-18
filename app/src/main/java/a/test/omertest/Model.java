package a.test.omertest;


import android.util.Log;

import java.util.List;

import a.test.omertest.model.AppDatabase;
import a.test.omertest.model.Feed;
import a.test.omertest.model.FeedItem;
import a.test.omertest.model.RoomItem;
import retrofit2.Response;

public class Model implements IModel {

    private AppDatabase db;
    private FeedPresenter.Resolver resolver;

    public Model(AppDatabase db, FeedPresenter.Resolver resolver) {
        this.db = db;
        this.resolver = resolver;
    }

    @Override
    public List<RoomItem> getItems() {
        return db.itemDAO().getAll();
    }

    @Override
    public void saveFeed(Response<Feed> feedResponse) {
        if (feedResponse.body() != null) {
            for (FeedItem fi : feedResponse.body().getChannel().getFeedItems()) {
                db.itemDAO().insert(new RoomItem(fi));
                Log.e("Title  :  ", fi.getTitle());
            }
        }
    }

    @Override
    public boolean isOnline() {
        return resolver.isNetworkAvailable();
    }
}
