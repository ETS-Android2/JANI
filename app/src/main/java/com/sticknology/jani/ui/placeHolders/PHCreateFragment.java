package com.sticknology.jani.ui.placeHolders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani.R;
import com.sticknology.jani.data.TrainingPlan;
import com.sticknology.jani.dataProcessing.InterpretTrainingPlan;
import com.sticknology.jani.dataProcessing.StandardReadWrite;
import com.sticknology.jani.ui.create.planCreation.PlanCreationActivity;

import java.util.ArrayList;

public class PHCreateFragment extends Fragment {

    public static ArrayList<TrainingPlan> trainingPlanList;
    private PHCreateAdapter phCreateAdapter;
    private RecyclerView planRev;

    public static PHCreateFragment newInstance(String text) {

        PHCreateFragment f = new PHCreateFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        //Set Listener for Bottom Button To Create New Plan
        Button newButton = getView().findViewById(R.id.button_new_plan_manage);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlanCreationActivity.currentTabSet = PlanCreationActivity.TABSET.VIEW;
                Intent newPlanActivity = new Intent(getActivity().getApplicationContext(), PlanCreationActivity.class);
                startActivity(newPlanActivity);
            }
        });

        trainingPlanList = new ArrayList<>();
        String readPlanFile = new StandardReadWrite().readFileToString("training_plans.txt", getContext());
        String[] readFileSplit = readPlanFile.split("\n");
        for(int i = 2; i < readFileSplit.length; i++){
            trainingPlanList.add(new InterpretTrainingPlan().getTrainingPlanFromString(readFileSplit[i]));
        }
        planRev = getView().findViewById(R.id.rev_manage);
        phCreateAdapter = new PHCreateAdapter(trainingPlanList);
        planRev.setAdapter(phCreateAdapter);
        planRev.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.plan_bar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.abar_plan_delete){
            //Iterate through child items of recycler view, index based off of adapter
            for(int i = 0; i < phCreateAdapter.getItemCount(); i++){
                //Get Child View, update text and set listener for items
                View mChild = planRev.getChildAt(i);
                Button nowDelete = mChild.findViewById(R.id.rev_setactive_trainingplan);
                nowDelete.setText("Delete");
                int index = i;
                nowDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Sets the version of the template file
                        String build = getString(R.string.file_encoding);

                        trainingPlanList.remove(index);
                        for(int u = 0; u < trainingPlanList.size(); u++){
                            build += "\n" + new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlanList.get(u));
                        }
                        new StandardReadWrite().writeFile(build, "training_plans.txt", getContext());
                        //trainingPlanList = new InterpretTrainingPlan().getRunTemplates(getContext());
                        //System.err.println("THIS IS SIZE: " + runList.size());
                        phCreateAdapter.notifyDataSetChanged();

                    }
                });
            }

            return true;
        } else if(item.getItemId() == R.id.abar_plan_edit) {

            /*//Iterate through child items of recycler view, index based off of adapter
            for(int i = 0; i < phCreateAdapter.getItemCount(); i++){
                //Get Child View, update text and set listener for items
                View mChild = planRev.getChildAt(i);
                Button nowDelete = mChild.findViewById(R.id.rev_setactive_trainingplan);
                nowDelete.setText("Delete");
                int index = i;
                nowDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Sets the version of the template file
                        String build = getString(R.string.file_encoding);

                        trainingPlanList.remove(index);
                        for(int u = 0; u < trainingPlanList.size(); u++){
                            build += "\n" + new InterpretTrainingPlan().getStringFromTrainingPlan(trainingPlanList.get(u));
                        }
                        new StandardReadWrite().writeFile(build, "training_plans.txt", getContext());
                        //trainingPlanList = new InterpretTrainingPlan().getRunTemplates(getContext());
                        //System.err.println("THIS IS SIZE: " + runList.size());
                        phCreateAdapter.notifyDataSetChanged();

                    }
                });
            }*/

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}

