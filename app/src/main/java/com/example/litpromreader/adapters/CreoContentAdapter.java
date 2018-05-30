package com.example.litpromreader.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.litpromreader.R;
import com.example.litpromreader.constant.TextSizeConstant;
import com.example.litpromreader.model.LitpromPostText;
import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoInfo;
import com.example.litpromreader.model.creoContent.CreoString;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 25.11.2017.
 */

public class CreoContentAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private LitpromPostText litpromPostText;
    private ArrayList<CreoContent> creoContentArrayList;
    private int textSize= TextSizeConstant.defTextSize;


    public CreoContentAdapter(Context context, LitpromPostText litpromPostText, int textSize) {
        this.context = context;

        this.litpromPostText = litpromPostText;
        creoContentArrayList = new ArrayList<>();

        creoContentArrayList = litpromPostText.getCreoContentArrayList();

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
        } else if(creoContent instanceof CreoInfo){
            view = layoutInflater.inflate(R.layout.creo_item, parent, false);
            ((TextView) view.findViewById(R.id.autorTextView)).setText(litpromPostText.getAutor().getName());
            ((TextView) view.findViewById(R.id.creoNameTextVew)).setText(litpromPostText.getLitpromCreotive().getName());
            ((TextView) view.findViewById(R.id.sectionNameTextView)).setText(litpromPostText.getLitpromSection().getName());
            Bitmap bitmap = litpromPostText.getAutor().getImageBitmap();
            if(bitmap != null)
                ((ImageView) view.findViewById(R.id.autorView)).setImageBitmap(bitmap);
        }
        return view;
    }




}
