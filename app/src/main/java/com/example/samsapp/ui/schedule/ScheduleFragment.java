package com.example.samsapp.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.samsapp.R;
import com.example.samsapp.databinding.FragmentScheduleBinding;
import com.example.samsapp.ui.schedule.ScheduleViewModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button = binding.button;
        Spinner spinner = binding.spinner;
        Spinner spinner1 = binding.spinner2;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected = spinner.getSelectedItem().toString();
                String day = spinner1.getSelectedItem().toString();
                try {
                    MyCompanyAdapter2 adapter = null;
                    adapter = new MyCompanyAdapter2(getContext(), run(selected, day));
                    ListView lv = (ListView) binding.listView2;
                    lv.setAdapter(adapter);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return root;
    }

    public Group[] run(String selected_group, String day) throws IOException, JSONException {
        Company compan = ReadJSONExample.readCompanyJSONFile(this, selected_group, day);
        Group[] arr = new Group[1];
        Group group = new Group();
        group.group = compan.getGroup();
        group.day = compan.getDay();
        group.schedule = new ArrayList<>();
        for(int i = 0; i < compan.getSchedule().length(); i++){
            group.schedule.add((String) compan.getSchedule().get(i));
        }
        arr[0] = group;
        return arr;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}