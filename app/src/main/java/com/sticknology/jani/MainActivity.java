package com.sticknology.jani;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;
import com.sticknology.jani.databinding.ActivityMainBinding;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static TrainingPlan aTrainingPlan;
    public static int activeDayIndex;
    public static int aDay;
    public static int aWeek;
    public static int dayNameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#002C47")));


        String readActive = new StandardReadWrite().readFileToString("active_plan.txt", this);
        String[] activeArray = readActive.split("\n");
        if (activeArray.length == 4) {

            String startDate = activeArray[2];
            aTrainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(activeArray[3]);

            String[] tplans = new StandardReadWrite().readFileToString("training_plans.txt", this).split("\n");
            for(int i = 2; i < tplans.length; i++){
                if(new InterpretTrainingPlan().getTrainingPlanFromString(tplans[i]).getTrainingPlanActive().equals("ACTIVE")){
                    aTrainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(tplans[i]);
                }
            }

            long diffInMillies = -1;
            Date secondDate = Calendar.getInstance().getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

            try {
                Date firstDate = sdf.parse(startDate);
                diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(diffInMillies < aTrainingPlan.getTrainingPlanWeeks().size()*(6.048*(10^8))) {

                Long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                String dayofweek = android.text.format.DateFormat.format("EEEE", secondDate).toString();
                String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                for (int i = 0; i < dayArray.length; i++) {
                    if (dayofweek.equals(dayArray[i])) {

                        dayNameIndex = i;
                    }
                }

                activeDayIndex = diff.intValue();

                aDay = activeDayIndex % 7;
                aWeek = activeDayIndex / 7;

            }
            //Else is run if there is no time left in current training plan
            else {
                //Delete active plan file
                File planFile = new File("active_plan.txt");
                planFile.delete();

                //Remove Active Tag From TrainingPlan
                for(int i = 2; i < tplans.length; i++){
                    TrainingPlan trainingPlan = new InterpretTrainingPlan().getTrainingPlanFromString(tplans[i]);
                    if(trainingPlan.getTrainingPlanActive().equals("ACTIVE")){
                        trainingPlan.setTrainingPlanActive(" ");
                        tplans[i] = new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlan);
                    }
                }

                String build = "";
                for(int i = 0; i < tplans.length; i++){
                    build += tplans[i];
                    if(i < tplans.length-1){
                        build += "\n";
                    }
                }

                new StandardReadWrite().writeFile(build, "training_plans", this);

                aTrainingPlan = null;
                activeDayIndex = 0;
                aDay = 0;
                aWeek = 0;
            }

        } else {
            aTrainingPlan = null;
            activeDayIndex = 0;
            aDay = 0;
            aWeek = 0;
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_calendar, R.id.navigation_plan, R.id.navigation_create,
                R.id.navigation_profile).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //Creating and setting files if they are not already present
        if(!(new File(this.getFilesDir(), "active_plan.txt").exists())){
            new StandardReadWrite().appendText("VERSION:1.0", "active_plan.txt", this, false);
        }
        if(!(new File(this.getFilesDir(), "run_templates.txt").exists())){
            new StandardReadWrite().appendText("VERSION:1.0", "run_templates.txt", this, false);
        }
        if(!(new File(this.getFilesDir(), "workout_templates.txt").exists())){
            new StandardReadWrite().appendText("VERSION:1.0", "workout_templates.txt", this, false);
        }
        if(!(new File(this.getFilesDir(), "training_plans.txt").exists())){
            new StandardReadWrite().appendText("VERSION:1.0", "training_plans.txt", this, false);
        }
    }

}