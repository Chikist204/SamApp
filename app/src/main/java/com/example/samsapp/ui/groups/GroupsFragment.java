package com.example.samsapp.ui.groups;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.samsapp.DatabaseHelper;
import com.example.samsapp.R;
import com.example.samsapp.Students;
import com.example.samsapp.databinding.FragmentGroupsBinding;

import java.util.ArrayList;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private FragmentGroupsBinding binding;
    DatabaseHelper STConnector;
    Context mContext;
    ListView sListView;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;
    myListAdapter myAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        binding = FragmentGroupsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sListView = (ListView) binding.listView4;
        Button button = binding.button2;
        STConnector = new DatabaseHelper(getContext());
        myAdapter = new myListAdapter(getContext(), STConnector.selectAll());
        sListView.setAdapter(myAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(STConnector.selectAll().get(0).t);
            }
            });

        return root;
    }

    private void updateList () {
        myAdapter.setArrayMyData(STConnector.selectAll());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // ADAPTER
    class myListAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private ArrayList<Students> arrayMyStudents;

        public myListAdapter(Context ctx, ArrayList<Students> arr) {
            mLayoutInflater = LayoutInflater.from(ctx);
            setArrayMyData(arr);
        }

        public ArrayList<Students> getArrayMyData() {
            return arrayMyStudents;
        }

        public void setArrayMyData(ArrayList<Students> arrayMyData) {
            this.arrayMyStudents = arrayMyData;
        }

        public int getCount() {
            return arrayMyStudents.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            Students md = arrayMyStudents.get(position);
            if (md != null) {
                return md.getId();
            }
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
                convertView = mLayoutInflater.inflate(R.layout.adapter_item3, null);

            TextView name = (TextView) convertView.findViewById(R.id.textView32);
            TextView group = (TextView) convertView.findViewById(R.id.textView23);


            Students md = arrayMyStudents.get(position);
            name.setText(md.getName());
            group.setText(md.getGroups());

            return convertView;
        }
    }//end myAdapter;

}