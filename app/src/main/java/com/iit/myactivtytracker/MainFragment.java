package com.iit.myactivtytracker;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by slouma on 20/02/2015.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                Log.v("slim","button clicked");
                break;

        }
    }
}
