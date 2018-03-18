package a.test.omertest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import a.test.omertest.R;
import a.test.omertest.model.AppDatabase;
import a.test.omertest.model.RoomItem;

public class ItemFragment extends Fragment implements
        SwipeRefreshLayout.OnDragListener,
        SwipeRefreshLayout.OnTouchListener {

    private static final String ITEM_POSITION = "position";

    public ItemFragment() {
    }

    public static ItemFragment newInstance(int position) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(ITEM_POSITION);
        }
        final RoomItem item = AppDatabase.getDatabase(getActivity()).itemDAO().getAll().get(position);
        View v = inflater.inflate(R.layout.fragment_item, container, false);
        ((TextView) v.findViewById(R.id.tvTitle)).setText(item.getTitle());
        ((TextView) v.findViewById(R.id.tvPubDate)).setText(item.getPubDate());
        ((TextView) v.findViewById(R.id.tvLink)).setText(item.getLink());
        if (item.getDescription() != null) {
            ((TextView) v.findViewById(R.id.tvDescription)).setText(item.getDescription());
        }
        return v;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
