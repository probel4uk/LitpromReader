package com.example.litpromreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.litpromreader.MainActivity;
import com.example.litpromreader.R;
import com.example.litpromreader.model.Pulse;
import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoString;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 25.12.2017.
 */

public class PulseAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Pulse> pulseArrayList;
    int textSize;

    public PulseAdapter(Context context, ArrayList<Pulse> pulseArrayList, int textSize ){
        this.context = context;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.pulseArrayList = pulseArrayList;
        this.textSize = textSize;
    }

    @Override
    public int getCount() {
        return pulseArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return pulseArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = lInflater.inflate(R.layout.pulse_item, parent, false);
//        if (view == null) {
//            view = lInflater.inflate(R.layout.pulse_item, parent, false);
//        }
        final Pulse pulse = pulseArrayList.get(position);
        ((TextView) view.findViewById(R.id.commentNumTextView)).setText(String.valueOf(position));
        ((TextView) view.findViewById(R.id.creoNameTextView)).setText(pulse.getLitpromCreotive().getName());
        ((TextView) view.findViewById(R.id.commentAuthorTextView)).setText(pulse.getAuthorComment().getName());

        LinearLayout commentContenLayout = (LinearLayout) view.findViewById(R.id.commentContentLayout) ;
        ArrayList<CreoContent> commentContentArrayList = pulse.getCommentContentArrayList();

        for(CreoContent creoContent2: commentContentArrayList){
            if(creoContent2 instanceof CreoString){
                CreoString creoString = (CreoString)creoContent2;



                TextView textView = new TextView(context);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                textView.setText("\t" + creoString.getString());
                commentContenLayout.addView(textView);

            }

        }
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, MainActivity.class);
                myIntent.putExtra("creoUrl", pulse.getLitpromCreotive().getUrl());
                context.startActivity(myIntent);
            }
        });




        return view;
    }
}
