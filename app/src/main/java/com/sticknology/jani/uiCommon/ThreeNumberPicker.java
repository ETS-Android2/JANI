package com.sticknology.jani.uiCommon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.sticknology.jani.R;
import com.sticknology.jani.data2.MyTime;

public class ThreeNumberPicker {

    public void threePickerDialog(Button buttonObject, View view, Activity activity, Context context, String type){

        buttonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //build alert dialog
                final AlertDialog.Builder d = new AlertDialog.Builder(context);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_three_number_picker, null);
                d.setTitle("Set " + type);
                d.setView(dialogView);

                //Type initialization
                int firstPosition = 0;
                int secondPosition = 0;
                int thirdPosition = 0;
                //Picker1 max, Picker1 min, Picker2 max, Picker2 min, Picker3 max, Picker3 min
                int[] pickerBounds = new int[6];
                if(type.equals("Time")){
                    int[] posArray = getTimePositions(buttonObject.getText().toString());
                    firstPosition = posArray[0];
                    secondPosition = posArray[1];
                    thirdPosition = posArray[2];
                    pickerBounds = new int[]{20, 0, 59, 0, 59, 0};
                }

                //Set behavior for first picker
                final NumberPicker picker1 = (NumberPicker) dialogView.findViewById(R.id.d3number_picker1);
                picker1.setMaxValue(pickerBounds[0]);
                picker1.setMinValue(pickerBounds[1]);
                picker1.setWrapSelectorWheel(true);
                //Get current set value
                if(firstPosition <= picker1.getMaxValue() && firstPosition >= picker1.getMinValue()){
                    picker1.setValue(firstPosition);
                }
                picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    }
                });

                //Set behavior for second number picker
                final NumberPicker picker2 = (NumberPicker) dialogView.findViewById(R.id.d3number_picker2);
                picker2.setMaxValue(pickerBounds[2]);
                picker2.setMinValue(pickerBounds[3]);
                picker2.setWrapSelectorWheel(true);
                //Get current set value
                if(secondPosition <= picker2.getMaxValue() && secondPosition >= picker2.getMinValue()){
                    picker2.setValue(secondPosition);
                }
                picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    }
                });

                //Set behavior for third number picker
                final NumberPicker picker3 = (NumberPicker) dialogView.findViewById(R.id.d3number_picker3);
                picker3.setMaxValue(pickerBounds[4]);
                picker3.setMinValue(pickerBounds[5]);
                picker3.setWrapSelectorWheel(true);
                //Get current set value
                if(thirdPosition <= picker3.getMaxValue() && thirdPosition >= picker3.getMinValue()){
                    picker3.setValue(thirdPosition);
                }
                picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    }
                });

                //Set button behavior
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (type.equals("Time")) {
                            String time = new MyTime(picker1.getValue(), picker2.getValue(),
                                    picker3.getValue()).getDispString();
                            buttonObject.setText(time);
                        }
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

    private int[] getTimePositions(String input){

        int[] posArray = new int[3];
        String[] prevPace = input.split(":");
        if(prevPace.length == 3){
            posArray[0] = Integer.parseInt(prevPace[0]);
            posArray[1] = Integer.parseInt(prevPace[1]);
            posArray[2] = Integer.parseInt(prevPace[2]);
        } else if(prevPace.length == 2){
            posArray[0] = 0;
            posArray[1] = Integer.parseInt(prevPace[0]);
            posArray[2] = Integer.parseInt(prevPace[1]);
        }

        return posArray;
    }
}
