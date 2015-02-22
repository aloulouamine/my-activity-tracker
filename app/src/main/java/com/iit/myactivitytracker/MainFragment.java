package com.iit.myactivitytracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.iit.myactivitytracker.adapter.CustomAdapter;
import com.iit.myactivitytracker.ui.AddDialog;
import com.iit.myactivitytracker.wrapper.ListItemWrapper;

import java.util.ArrayList;

/**
 * Created by slouma on 21/02/2015.
 */
public class MainFragment extends Fragment implements AddDialog.OnAddListener {


    private static final String LIST_CONTENT_KEY = "list_content_key";
    private CustomAdapter mAdapter;
    private ArrayList<ListItemWrapper> mObjectList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        if (savedInstanceState == null) {
            mObjectList = new ArrayList<ListItemWrapper>();
        } else {
            mObjectList = (ArrayList<ListItemWrapper>) savedInstanceState.getSerializable(LIST_CONTENT_KEY);
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CustomAdapter(mObjectList);
        recyclerView.setAdapter(mAdapter);
        setHasOptionsMenu(true);
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putSerializable(LIST_CONTENT_KEY, mObjectList);

        super.onSaveInstanceState(outState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            AddDialog addDialog = AddDialog.newInstance(this);
            addDialog.show(getActivity().getSupportFragmentManager(), "");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOkClicked(ListItemWrapper listItemWrapper) {
        Log.v("slim", "catch ok button click :" + listItemWrapper.getTitle());
        mObjectList.add(listItemWrapper);
        mAdapter.notifyDataSetChanged();
    }
}
