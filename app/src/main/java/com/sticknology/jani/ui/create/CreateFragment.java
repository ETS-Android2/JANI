package com.sticknology.jani.ui.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sticknology.jani.R;
import com.sticknology.jani.databinding.FragmentCreateBinding;

public class CreateFragment extends Fragment {

    private CreateViewModel createViewModel;
    private FragmentCreateBinding binding;
    private final String[] titles = new String[]{"Manage", "Export"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        ViewPager2 viewPager2 = getView().findViewById(R.id.create_pager2);
        CreatePager createPager = new CreatePager(this);
        viewPager2.setAdapter(createPager);
        TabLayout myTabLayout = getView().findViewById(R.id.create_tab);
        new TabLayoutMediator(myTabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}