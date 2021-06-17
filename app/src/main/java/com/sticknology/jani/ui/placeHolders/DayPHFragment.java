package com.sticknology.jani.ui.placeHolders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sticknology.jani.MainActivity;
import com.sticknology.jani.R;
import com.sticknology.jani.data.Interval;
import com.sticknology.jani.data.Run;
import com.sticknology.jani.data.TrainingDay;
import com.sticknology.jani.data.Workout;
import com.sticknology.jani.dataProcessing.InterpretRun;

import java.util.List;

public class DayPHFragment extends Fragment {

    private int showDay;
    private int showWeek;
    private int dayName;

    private Button nextDay;
    private Button prevDay;
    private TextView dayTitle;
    private TextView dayType;
    private TextView rTitle;
    private TextView wTitle;
    private TextView runsText;
    private TextView workoutsText;

    public DayPHFragment newInstance(int test){

        DayPHFragment f = new DayPHFragment();
        Bundle b = new Bundle();

        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        showDay = MainActivity.aDay;
        showWeek = MainActivity.aWeek;
        dayName = MainActivity.dayNameIndex;
        return inflater.inflate(R.layout.ph_fragment_day, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        nextDay = getView().findViewById(R.id.ph_day_next);
        prevDay = getView().findViewById(R.id.ph_day_previous);
        dayTitle = getView().findViewById(R.id.ph_day_title);
        dayType = getView().findViewById(R.id.ph_day_type);
        rTitle = getView().findViewById(R.id.ph_day_rtitle);
        wTitle = getView().findViewById(R.id.ph_day_wtitle);
        runsText = getView().findViewById(R.id.ph_day_runs);
        workoutsText = getView().findViewById(R.id.ph_day_workouts);

        if(MainActivity.aTrainingPlan == null){

            nextDay.setVisibility(View.GONE);
            prevDay.setVisibility(View.GONE);
            dayType.setVisibility(View.GONE);
            rTitle.setVisibility(View.GONE);
            wTitle.setVisibility(View.GONE);
            runsText.setVisibility(View.GONE);
            workoutsText.setVisibility(View.GONE);

            dayTitle.setText("No Active Plan");
        }
        else {
            setDayView();
        }

        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(MainActivity.aTrainingPlan.getTrainingPlanWeeks().size() + "This is trianing weeks size");

                if(!(showDay+1 == 7 && showWeek+1 == MainActivity.aTrainingPlan.getTrainingPlanWeeks().size())){
                    showDay++;
                    if(showDay == 7){
                        showDay = 0;
                        showWeek++;
                    }
                    updateDayNameIndex(true);
                    setDayView();
                }
            }
        });

        prevDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(showDay-1 == -1 && showWeek-1 == -1)){
                    showDay--;
                    if(showDay == -1){
                        showDay = 6;
                        showWeek--;
                    }
                    updateDayNameIndex(false);
                    setDayView();
                }
            }
        });
    }

    private void updateDayNameIndex(boolean forward){

        if(forward){
            dayName++;
        } else {
            dayName--;
        }
        if(dayName == 7){
            dayName = 0;
        } else if(dayName == -1){
            dayName = 6;
        }
    }

    private void setDayView(){

        TextView localDayTitle = dayTitle;
        TextView localWorkouts = workoutsText;
        TextView localRuns = runsText;
        TextView localRTitle = rTitle;
        TextView localWTitle = wTitle;
        TextView localDayType = dayType;

        String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        localDayTitle.setText(dayArray[dayName]);

        TrainingDay currentDay = MainActivity.aTrainingPlan.getTrainingDay(showWeek, showDay);

        //Sets Training Day Type
        if(!currentDay.getTrainingDayType().equals(" ")) {
            localDayType.setVisibility(View.VISIBLE);
            localDayType.setText(currentDay.getTrainingDayType());
        } else {
            localDayType.setVisibility(View.GONE);
        }

        List<Workout> workoutList = currentDay.getTrainingDayWorkouts();
        if(!workoutList.get(0).getWorkoutName().equals(":;:")) {
            localWTitle.setText("Workouts:");
            localWorkouts.setVisibility(View.VISIBLE);
            String workoutBuildString = "";
            for (int i = 0; i < workoutList.size(); i++) {
                workoutBuildString += workoutList.get(i).getWorkoutName();
                workoutBuildString += "\n" + workoutList.get(i).getWorkoutType();
                workoutBuildString += "\n" + workoutList.get(i).getWorkoutDescriptor() + "\n\n";
            }

            localWorkouts.setText(workoutBuildString);
        } else{
            localWorkouts.setVisibility(View.GONE);
            localWTitle.setText("No Workouts Today");
        }

        List<Run> runList = currentDay.getTrainingDayRuns();
        for(int i = 0; i < runList.size(); i++){
            System.out.println("THIS IS RUNLIST: " + new InterpretRun().getStringRun(runList.get(i)));
        }
        if(!runList.get(0).getRunName().equals(":;:")) {
            localRuns.setVisibility(View.VISIBLE);
            localRTitle.setText("Runs:");
            String runBuildString = "";
            for (int i = 0; i < runList.size(); i++) {
                runBuildString += runList.get(i).getRunName() + ":  " + runList.get(i).getRunType();
                runBuildString += "\n" + runList.get(i).getRunDescriptor();
                List<Interval> intervalList = runList.get(i).getRunIntervals();
                for(int u = 0; i < intervalList.size(); i++){
                    runBuildString += "\n" + "Effort: " + intervalList.get(u).getIntervalEffort() + "   Distance: " + intervalList.get(u).getIntervalDistance();
                    runBuildString += "\n" + "Pace: " + intervalList.get(u).getIntervalPace() + "   Time: " + intervalList.get(u).getIntervalTime() + "\n";
                }
            }

            localRuns.setText(runBuildString);
        } else{
            localRuns.setVisibility(View.GONE);
            localRTitle.setText("No Runs Today");
        }
    }
}
