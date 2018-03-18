package a.test.omertest.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import a.test.omertest.R;
import a.test.omertest.model.RoomItem;

public class ItemViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
    private TextView pubDate;
    private TextView title;
    private TextView link;
    private TextView description;

    public ItemViewHolder(View itemView) {
        super(itemView);
        pubDate = itemView.findViewById(R.id.tvPubDate);
        title = itemView.findViewById(R.id.tvTitle);
        link = itemView.findViewById(R.id.tvLink);
        description = itemView.findViewById(R.id.tvDescription);
    }

    public void setHolder(RoomItem item) {
        pubDate.setText(item.getPubDate());
        title.setText(item.getTitle());
        if (description != null) {
            description.setText(item.getDescription());
        }
        link.setText(item.getLink());
    }
}