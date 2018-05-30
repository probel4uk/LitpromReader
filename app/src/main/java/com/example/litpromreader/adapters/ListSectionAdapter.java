package com.example.litpromreader.adapters;

import android.content.Context;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.litpromreader.MainActivity;
import com.example.litpromreader.R;
import com.example.litpromreader.model.LitpromSection;

import java.util.ArrayList;

/**
 * Created by Андрей on 06.12.2017.
 */

public class ListSectionAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<LitpromSection> litpromSections;



    public ListSectionAdapter(Context context, ArrayList<LitpromSection> litpromSections) {
        this.context=context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.litpromSections = litpromSections;

    }

    @Override
    public int getCount() {
        return litpromSections.size();
    }

    @Override
    public Object getItem(int i) {
        return litpromSections.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.section_item, parent, false);
        }
        final LitpromSection litpromSection = litpromSections.get(position);
        ((TextView)view.findViewById(R.id.sectionNameTextView)).setText(litpromSection.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, MainActivity.class);
                myIntent.putExtra("sectionUrl", litpromSection.getUrl());
                context.startActivity(myIntent);
        }
        });

        return view;
    }
}
