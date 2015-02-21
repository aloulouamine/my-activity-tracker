package com.iit.myactivitytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iit.myactivitytracker.adapter.CustomAdapter;
import com.iit.myactivitytracker.wrapper.ListItemWrapper;

import java.util.ArrayList;

/**
 * Created by slouma on 21/02/2015.
 */
public class MainFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        ArrayList<ListItemWrapper> objectListList = new ArrayList<ListItemWrapper>();
        for (int i = 0; i < 1000; i++) {
            ListItemWrapper wrapper = new ListItemWrapper(R.drawable.ic_launcher, "item n° " + i,
                    "description " + i, "state " + i);
            objectListList.add(wrapper);
        }


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        CustomAdapter mAdapter = new CustomAdapter(objectListList);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
