package a.test.omertest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.test.omertest.IPresenter;
import a.test.omertest.R;
import a.test.omertest.adapters.viewholders.ItemViewHolder;
import a.test.omertest.model.RoomItem;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<RoomItem> feedItems = new ArrayList<>();
    private IPresenter feedPresenter;

    public ItemsListAdapter(IPresenter feedPresenter) {
        this.feedPresenter = feedPresenter;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_rv_item, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedPresenter.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setHolder(feedItems.get(position));
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItems(List<RoomItem> items) {
        feedItems.clear();
        feedItems.addAll(items);
        notifyDataSetChanged();
    }
}