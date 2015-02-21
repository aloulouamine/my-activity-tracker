package com.iit.myactivitytracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iit.myactivitytracker.R;
import com.iit.myactivitytracker.wrapper.ListItemWrapper;

import java.util.ArrayList;

/**
 * Created by slouma on 21/02/2015.
 */
public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<ListItemWrapper> mObjectsList = new ArrayList<ListItemWrapper>();

    public CustomAdapter(ArrayList<ListItemWrapper> itemsList) {

        mObjectsList = itemsList;
    }

    @Override
    public int getItemCount() {

        return mObjectsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ... Nothing to do
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mItemTitle.setText(mObjectsList.get(position).getTitle());
        holder.mItemTime.setText(mObjectsList.get(position).getTime());
        holder.mItemState.setText(mObjectsList.get(position).getState());
        holder.mItemIcon.setImageResource(mObjectsList.get(position)
                .getItemImage());
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemTitle;
        public TextView mItemTime;
        public TextView mItemState;
        public ImageView mItemIcon;

        public ViewHolder(View view) {
            super(view);
            mItemTitle = (TextView) view.findViewById(R.id.title);
            mItemTime = (TextView) view.findViewById(R.id.time);
            mItemState = (TextView) view.findViewById(R.id.state);
            mItemIcon = (ImageView) view.findViewById(R.id.image);

        }
    }

}
