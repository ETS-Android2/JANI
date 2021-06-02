package com.sticknology.jani.dataProcessing;

import android.content.Context;
import android.util.Log;

import com.sticknology.jani.data.Run;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReadWriteRun {
    public void writeRunToText(String name, String description, String[] distance, String[] time,
                               int[] efforts, String[] pace, Context context) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("run_save1.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(name + "|" + description + "\n");
            for(int i = 0; i < distance.length; i++){
                outputStreamWriter.write(distance[i] + "|" + time[i] + "|" + pace[i] + "|" + efforts[i] + "\n");
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public Run readRunToObject(Context context){
        Run ret = null;

        try {
            InputStream inputStream = context.openFileInput("run_save1.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = null;
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
