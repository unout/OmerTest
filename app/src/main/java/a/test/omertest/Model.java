package a.test.omertest;


import a.test.omertest.model.Feed;
import a.test.omertest.model.FeedItem;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class Model implements IModel {

    private Realm realm;

    public Model(Realm realm) {
        this.realm = realm;
    }

    @Override
    public RealmResults<FeedItem> getItems() {
        return Realm.getDefaultInstance().where(FeedItem.class).findAll();
    }

    @Override
    public void clear() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    @Override
    public void saveFeed(Response<Feed> feedResponse) {
        try {
            realm.beginTransaction();
            realm.deleteAll();
            realm.insert(feedResponse.body().getChannel());
            realm.commitTransaction();
        } finally {
            realm.close();
        }
    }

    public void isNetworkAvailable() {
    }
}
