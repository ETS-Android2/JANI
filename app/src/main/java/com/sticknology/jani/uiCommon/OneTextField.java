package com.sticknology.jani.uiCommon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sticknology.jani.R;

import static com.sticknology.jani.run2.DispRun2Fragment.dispRun;

public class OneTextField {


    public void OneTextField(TextView textView, Activity activity, Context context){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //build alert dialog
                final AlertDialog.Builder d = new AlertDialog.Builder(context);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_one_textfield, null);
                d.setTitle("Set Notes");
                d.setView(dialogView);

                EditText editText = dialogView.findViewById(R.id.d1tfield_tfield);
                String currentNotes = textView.getText().toString();
                if(!currentNotes.equals("Tap to Add Notes") && !currentNotes.equals("")){
                    editText.setText(currentNotes);
                }

                //Set button behavior
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dispRun.setNotes(editText.getText().toString());
                        textView.setText(dispRun.getNotes());
                    }
                });
                d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                //Final show
                AlertDialog alertDialog = d.create();
                alertDialog.show();
            }
        });
    }
}
