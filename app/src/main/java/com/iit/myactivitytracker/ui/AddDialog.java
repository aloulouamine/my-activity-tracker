package com.iit.myactivitytracker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.iit.myactivitytracker.R;
import com.iit.myactivitytracker.wrapper.ListItemWrapper;

/**
 * Created by slouma on 21/02/2015.
 */
public class AddDialog extends DialogFragment {


    private static OnAddListener mListener;

    private EditText mEditText;
    private TimePicker mTimePicker;
    private Spinner mSpinner;


    public static AddDialog newInstance(OnAddListener listener) {

        AddDialog dialog = new AddDialog();
        mListener = listener;
        return dialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog_layout, null);

        initViews(view);

        Dialog dialog = builder
                .setTitle(R.string.add_dialog_title).setView(view)
                .setPositiveButton(
                        getResources().getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mListener.onOkClicked(createListItem());
                            }
                        })
                .setNegativeButton(
                        getResources().getString(android.R.string.cancel),
                        null).create();

        return dialog;
    }

    private void initViews(View view) {
        mEditText = (EditText) view.findViewById(R.id.edit_text);
        mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
    }

    private ListItemWrapper createListItem() {


        ListItemWrapper listItemWrapper = new ListItemWrapper();
        listItemWrapper.setTitle(mEditText.getText().toString());

        String time = "" + mTimePicker.getCurrentHour() + ":" + mTimePicker.getCurrentMinute();
        listItemWrapper.setTime(time);


        String state = getResources().getStringArray(R.array.add_dialog_spinner_items)[mSpinner.getSelectedItemPosition()];
        listItemWrapper.setState(state);

        return listItemWrapper;
    }

    public interface OnAddListener {

        public void onOkClicked(ListItemWrapper listItemWrapper);


    }

}
