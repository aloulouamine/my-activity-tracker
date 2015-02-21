package com.iit.myactivitytracker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by slouma on 21/02/2015.
 */
public class AddDialog extends DialogFragment {


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // LayoutInflater inflater = getActivity().getLayoutInflater();

        Dialog dialog = builder
                .setTitle("TestDialog")


                .setPositiveButton(
                        getResources().getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton(
                        getResources().getString(android.R.string.cancel),
                        null).create();

        return dialog;
    }


}
