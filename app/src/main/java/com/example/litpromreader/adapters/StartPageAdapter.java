package com.example.litpromreader.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.litpromreader.R;
import com.example.litpromreader.constant.TextSizeConstant;
import com.example.litpromreader.model.LitpromPostText;
import com.example.litpromreader.model.StartPage;
import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoString;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 13.12.2017.
 */

public class StartPageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private StartPage startPage;
    private ArrayList<CreoContent> creoContentArrayList;
    private int textSize = TextSizeConstant.defTextSize;


    public StartPageAdapter(Context context, StartPage startPage, int textSize) {
        this.context = context;

        this.startPage = startPage;


        creoContentArrayList = startPage.getCreoContentArrayList();

        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.textSize = textSize;

    }

    @Override
    public int getCount() {
        return creoContentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return creoContentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        CreoContent creoContent = creoContentArrayList.get(position);

        if(creoContent instanceof CreoString){
            view = layoutInflater.inflate(R.layout.creo_paragraph, parent, false);
            CreoString CREO_STRING = (CreoString)creoContent;
            TextView textView  = (TextView)view.findViewById(R.id.paragraphTextView);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

            //((TextView)view.findViewById(R.id.paragraphTextView))
            textView
                    .setText("\t" + CREO_STRING.getString());
        }
        return view;
    }
}
