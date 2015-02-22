package com.iit.myactivitytracker;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.iit.myactivitytracker.adapter.CustomAdapter;
import com.iit.myactivitytracker.database.TrackerContentProvider;
import com.iit.myactivitytracker.database.tables.RecordsTable;
import com.iit.myactivitytracker.ui.AddDialog;
import com.iit.myactivitytracker.wrapper.ListItemWrapper;

import java.util.ArrayList;

/**
 * Created by slouma on 21/02/2015.
 */
public class MainFragment extends Fragment implements AddDialog.OnAddListener, LoaderManager.LoaderCallbacks {


    private static final String LIST_CONTENT_KEY = "list_content_key";
    private static final int RECORD_TABLE_ID = 1;


    private CustomAdapter mAdapter;
    private ArrayList<ListItemWrapper> mObjectList;
    private android.support.v4.app.LoaderManager mLoaderManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        if (savedInstanceState == null) {
            mObjectList = new ArrayList<ListItemWrapper>();
            mLoaderManager = getLoaderManager();
            mLoaderManager.initLoader(RECORD_TABLE_ID, null, this);
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
        ContentValues contentValues = new ContentValues();
        contentValues.put(RecordsTable.LABEL, listItemWrapper.getTitle());
        contentValues.put(RecordsTable.STATE, listItemWrapper.getState());
        contentValues.put(RecordsTable.TIME, listItemWrapper.getTime());
        getActivity().getContentResolver().insert(
                TrackerContentProvider.RECORDS_CONTENT_URI,
                contentValues);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        Uri baseUri = TrackerContentProvider.RECORDS_CONTENT_URI;

        return new CursorLoader(getActivity(), baseUri,
                RecordsTable.PROJECTION_ALL, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (loader.getId() == RECORD_TABLE_ID) {
            Cursor cursor = (Cursor) data;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mObjectList.add(createListItem(cursor));
                cursor.moveToNext();
            }
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onLoaderReset(Loader loader) {

    }


    private ListItemWrapper createListItem(Cursor data) {


        ListItemWrapper listItemWrapper = new ListItemWrapper();
        listItemWrapper.setTitle(data.getString(data.getColumnIndex(RecordsTable.LABEL)));

        listItemWrapper.setTime(data.getString(data.getColumnIndex(RecordsTable.TIME)));


        listItemWrapper.setState(data.getString(data.getColumnIndex(RecordsTable.STATE)));
        mLoaderManager.destroyLoader(RECORD_TABLE_ID);
        return listItemWrapper;
    }
}
