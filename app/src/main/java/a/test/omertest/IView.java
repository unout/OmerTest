package a.test.omertest;

import java.util.List;

import a.test.omertest.model.RoomItem;

public interface IView {

    void showItems(List<RoomItem> items);

    void openItem(int position);

    void showNetworkErrorToast();

    void showCommonErrorToast(String log);
}
