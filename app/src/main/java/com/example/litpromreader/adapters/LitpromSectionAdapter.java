package com.example.litpromreader.adapters;


import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.litpromreader.MainActivity;
import com.example.litpromreader.R;
import com.example.litpromreader.model.LitpomPresText;

import java.util.ArrayList;

/**
 * Created by Андрей on 14.11.2017.
 */

public class LitpromSectionAdapter extends BaseAdapter {




    Context ctx;
    LayoutInflater lInflater;
    ArrayList<LitpomPresText> litpromTexts;

    public LitpromSectionAdapter(Context context, ArrayList<LitpomPresText> litpromTexts) {
        ctx = context;
        this.litpromTexts = litpromTexts;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return litpromTexts.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return litpromTexts.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.creo_item, parent, false);
        }

        final LitpomPresText litpomPresText= getLitpromText(position);
        // заполняем View в пункте списка данными
        // и картинка
        ((TextView) view.findViewById(R.id.autorTextView)).setText(litpomPresText.getAutor().getName());
        ((TextView) view.findViewById(R.id.creoNameTextVew)).setText(litpomPresText.getLitpromCreotive().getName());
        ((TextView) view.findViewById(R.id.sectionNameTextView)).setText(litpomPresText.getLitpromSection().getName());
        Bitmap bitmap = litpomPresText.getAutor().getImageBitmap();
        if(bitmap != null)
            ((ImageView) view.findViewById(R.id.autorView)).setImageBitmap(bitmap);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Intent myIntent = new Intent(ctx, MainActivity.class);

//                Bundle extras = new Bundle();
//                extras.putSerializable("litpomPresText", litpomPresText);
                myIntent.putExtra("creoUrl", litpomPresText.getLitpromCreotive().getUrl());
//                myIntent.putExtras(extras);
                ctx.startActivity(myIntent);
            }
        });






        return view;
    }



    private LitpomPresText getLitpromText(int position) {
        return ((LitpomPresText)getItem(position));
    }



}
