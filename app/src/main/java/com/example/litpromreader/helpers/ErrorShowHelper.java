package com.example.litpromreader.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.litpromreader.R;

/**
 * Created by Андрей on 08.12.2017.
 */

public class ErrorShowHelper {
    private ErrorShowHelper(){

    }
    public static View showErrorView(LayoutInflater inflater, ViewGroup container){

        View rootVew = inflater.inflate(R.layout.creo_paragraph, container, false);
        TextView textView = rootVew.findViewById(R.id.paragraphTextView);
        textView.setText("Ошибка Сети!");

        return rootVew;

    }
}
