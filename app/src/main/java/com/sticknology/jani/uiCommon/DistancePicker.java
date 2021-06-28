package com.sticknology.jani.uiCommon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.sticknology.jani.R;

import static android.content.ContentValues.TAG;

public class DistancePicker {

    public void distanceDialog(Button buttonObject, View view, Activity activity, Context context){

        buttonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //build alert dialog
                final AlertDialog.Builder d = new AlertDialog.Builder(context);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_distance_picker, null);
                d.setTitle("Title");
                d.setMessage("Message");
                d.setView(dialogView);

                //Get previous set distance
                int wholeDistance = 0;
                String decimalDistance = "";
                String prevDistance = buttonObject.getText().toString().split(" ")[0];
                if(prevDistance.contains(".")) {
                    wholeDistance = Integer.parseInt(prevDistance.split("\\.")[0]);
                    decimalDistance = "." + prevDistance.split("\\.")[1];
                }

                //Set behavior for whole number picker
                final NumberPicker wholePicker = (NumberPicker) dialogView.findViewById(R.id.ddp_wholewheel);
                wholePicker.setMaxValue(50);
                wholePicker.setMinValue(0);
                wholePicker.setWrapSelectorWheel(true);
                //Get current set value
                if(wholeDistance <= wholePicker.getMaxValue() && wholeDistance >= wholePicker.getMinValue()){
                    wholePicker.setValue(wholeDistance);
                }
                wholePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    }
                });

                //Set behavior for decimal number picker
                final NumberPicker decimalPicker = (NumberPicker) dialogView.findViewById(R.id.ddp_decimalwheel);
                decimalPicker.setMaxValue(7);
                decimalPicker.setMinValue(0);
                String[] decimalValues = new String[]{".0", ".125", ".25", ".375", ".5", ".625", ".75", ".875"};
                decimalPicker.setDisplayedValues(decimalValues);
                decimalPicker.setWrapSelectorWheel(true);
                //Get current set value
                for(int i = 0; i < decimalPicker.getMaxValue(); i++){
                    if(decimalDistance.equals(decimalValues[i])){
                        decimalPicker.setValue(i);
                        break;
                    }
                }
                decimalPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    }
                });

                //Set button behavior
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: " + wholePicker.getValue());
                        buttonObject.setText(String.valueOf(wholePicker.getValue()) +
                                decimalValues[decimalPicker.getValue()] + " mi");
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
