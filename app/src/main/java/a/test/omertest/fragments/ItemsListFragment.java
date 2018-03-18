package a.test.omertest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import a.test.omertest.FeedPresenter;
import a.test.omertest.IPresenter;
import a.test.omertest.IView;
import a.test.omertest.R;
import a.test.omertest.adapters.ItemsListAdapter;
import a.test.omertest.model.AppDatabase;
import a.test.omertest.model.RoomItem;
import a.test.omertest.support.AndroidResolver;

public class ItemsListFragment extends Fragment implements IView, SwipeRefreshLayout.OnRefreshListener {

    private IPresenter feedPresenter;
    private ItemsListAdapter adapter;

    public static ItemsListFragment newInstance() {
        return new ItemsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getDatabase(getActivity());
        feedPresenter = new FeedPresenter(
                db,
                new AndroidResolver(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_items_list, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rvItemsList);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ItemsListAdapter(feedPresenter);
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedPresenter.attachView(this);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        feedPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showItems(List<RoomItem> items) {
        adapter.addItems(items);
    }

    @Override
    public void openItem(int position) {
        ItemFragment itemFragment = ItemFragment.newInstance(position);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, itemFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showNetworkErrorToast() {
        Toast.makeText(getActivity(), "Network is not available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCommonErrorToast(String log) {
        Toast.makeText(getActivity(), "Error: " + log, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        feedPresenter.refreshLayoutPulled();
    }
}
