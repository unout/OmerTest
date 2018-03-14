package a.test.omertest.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import a.test.omertest.R;
import a.test.omertest.model.FeedItem;

public class ItemViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
    private TextView pubDate;
    private TextView title;
    private TextView link;
    private TextView description;

    public ItemViewHolder(View itemView) {
        super(itemView);
        pubDate = itemView.findViewById(R.id.tvTitle);
        title = itemView.findViewById(R.id.tvLink);
        link = itemView.findViewById(R.id.tvPubDate);
        description = itemView.findViewById(R.id.tvDescription);
    }

    public void setHolder(FeedItem item) {
        pubDate.setText(item.getPubDate());
        title.setText(item.getTitle());
        if (description != null) {
            description.setText(item.getDescription());
        }
        link.setText(item.getLink());
    }
}