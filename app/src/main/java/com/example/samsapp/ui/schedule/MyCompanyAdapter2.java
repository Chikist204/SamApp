package com.example.samsapp.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.samsapp.R;

public class MyCompanyAdapter2 extends ArrayAdapter<Group> {

    public MyCompanyAdapter2(Context context, Group[] arr) {
        super(context, R.layout.adapter_item2, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Group group = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item2, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.textView11)).setText(group.group);
        ((TextView) convertView.findViewById(R.id.textView12)).setText(group.day);
        ((TextView) convertView.findViewById(R.id.textView3)).setText(group.schedule.get(0));
        ((TextView) convertView.findViewById(R.id.textView4)).setText(group.schedule.get(1));
        ((TextView) convertView.findViewById(R.id.textView5)).setText(group.schedule.get(2));
        ((TextView) convertView.findViewById(R.id.textView6)).setText(group.schedule.get(3));
        ((TextView) convertView.findViewById(R.id.textView7)).setText(group.schedule.get(4));
        ((TextView) convertView.findViewById(R.id.textView8)).setText(group.schedule.get(5));
        ((TextView) convertView.findViewById(R.id.textView9)).setText(group.schedule.get(6));
        ((TextView) convertView.findViewById(R.id.textView10)).setText(group.schedule.get(7));

        return convertView;
    }
}
