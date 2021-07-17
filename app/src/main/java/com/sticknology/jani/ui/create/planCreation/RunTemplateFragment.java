package com.sticknology.jani.ui.create.planCreation;

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
import com.sticknology.jani.data.Run;
import com.sticknology.jani.dataProcessing.InterpretRun;
import com.sticknology.jani.dataProcessing.StandardReadWrite;
import com.sticknology.jani.run2.EditRun2Fragment;

import java.util.ArrayList;

public class RunTemplateFragment extends Fragment {

    private int mDayPosition;
    private RunTemplateAdapter runTemplateAdapter;
    private RecyclerView templateRecyclerView;
    public static ArrayList<Run> runList;

    public static RunTemplateFragment newInstance(int dayPosition) {

        RunTemplateFragment f = new RunTemplateFragment();
        Bundle b = new Bundle();
        b.putInt("day", dayPosition);

        f.setArguments(b);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mDayPosition = getArguments().getInt("day");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_runtemplate, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newButton = getView().findViewById(R.id.button_newrun_template);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*RunCreationFragment runCreationFragment = RunCreationFragment.newInstance(mDayPosition);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, runCreationFragment, null)
                        .commit();*/

                /*DispRun2Fragment newFrag = DispRun2Fragment.newInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null).commit();*/

                //Going to EditRun2Fragment first to set initial title information
                EditRun2Fragment newFrag = EditRun2Fragment.newInstance("HEADER");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_create, newFrag, null).commit();
            }
        });

        //Get List of Workout Templates
        InterpretRun interpretRun = new InterpretRun();
        runList = interpretRun.getRunTemplates(getContext());

        //Sets up recyclerview displaying list of workout template items
        templateRecyclerView = getView().findViewById(R.id.runtemplate_recyclerview);
        runTemplateAdapter = new RunTemplateAdapter(runList, mDayPosition);
        templateRecyclerView.setAdapter(runTemplateAdapter);
        templateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.delete_button_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.abar_delete_button){
            //Iterate through child items of recycler view, index based off of adapter
            for(int i = 0; i < runTemplateAdapter.getItemCount(); i++){
                //Get Child View, update text and set listener for items
                View mChild = templateRecyclerView.getChildAt(i);
                Button nowDelete = mChild.findViewById(R.id.wov_button_add);
                nowDelete.setText("Delete");
                int index = i;
                nowDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Sets the version of the template file
                        String build = getString(R.string.file_encoding);;

                        runList.remove(index);
                        for(int u = 0; u < runList.size(); u++){
                            build += "\n" + new InterpretRun().getStringRun(runList.get(u));
                        }
                        new StandardReadWrite().writeFile(build, "run_templates.txt", getContext());
                        runList = new InterpretRun().getRunTemplates(getContext());
                        System.err.println("THIS IS SIZE: " + runList.size());
                        runTemplateAdapter.notifyDataSetChanged();

                    }
                });
            }

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
