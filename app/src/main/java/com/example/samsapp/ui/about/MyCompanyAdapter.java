package com.example.samsapp.ui.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.samsapp.R;
import com.example.samsapp.ui.schedule.Group;

public class MyCompanyAdapter extends ArrayAdapter<Comp> {

    public MyCompanyAdapter(Context context, Comp[] arr) {
        super(context, R.layout.adapter_item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Comp company = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.textView)).setText(company.key_about);
        ((TextView) convertView.findViewById(R.id.textView2)).setText(company.about);

        return convertView;
    }
}
