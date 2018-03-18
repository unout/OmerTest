package a.test.omertest;


import android.support.annotation.NonNull;
import android.util.Log;

import a.test.omertest.model.AppDatabase;
import a.test.omertest.model.Feed;
import a.test.omertest.support.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class FeedPresenter implements IPresenter {

    private IView view;
    private IModel model;

    public FeedPresenter(AppDatabase db, Resolver resolver) {
        model = new Model(db, resolver);
    }

    @Override
    public void onItemClick(int adapterPosition) {
        view.openItem(adapterPosition);
    }

    @Override
    public void attachView(IView view) {
        this.view = view;
        if (model.getItems() != null && model.getItems().size() > 0)
            view.showItems(model.getItems());
        loadFeed();
    }

    @Override
    public void detachView() {
        view = null;
    }

    private void loadFeed() {
        if (model.isOnline()) {
            setInitFinished(Constants.CODE_NETWORK_ERROR, null);
        } else {
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            Service service = restAdapter.create(Service.class);
            Call<Feed> call = service.getFeed();
            call.enqueue(new Callback<Feed>() {
                @Override
                public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                    model.saveFeed(response);
                    setInitFinished(Constants.CODE_SUCCESS, null);
                }

                @Override
                public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                    setInitFinished(Constants.CODE_COMMON_ERROR, t.toString());
                    Log.e("FATAL EXCEPTION! : ", t.toString());
                }
            });
        }
    }

    private void setInitFinished(int resultCode, String log) {
        if (view != null) {
            if (resultCode == Constants.CODE_SUCCESS) view.showItems(model.getItems());
            if (resultCode == Constants.CODE_NETWORK_ERROR) view.showNetworkErrorToast();
            if (resultCode == Constants.CODE_COMMON_ERROR) view.showCommonErrorToast(log);
        }
    }

    @Override
    public void refreshLayoutPulled() {
        loadFeed();
    }

    public interface Resolver {
        boolean isNetworkAvailable();
    }
}
