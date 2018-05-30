package com.example.litpromreader.model;

import com.example.litpromreader.model.creoContent.CreoContent;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 13.12.2017.
 */

public class StartPage {
    private ArrayList<CreoContent> creoContentArrayList;

    public StartPage(ArrayList<CreoContent> creoContentArrayList) {

        this.creoContentArrayList = creoContentArrayList;
    }

    public ArrayList<CreoContent> getCreoContentArrayList() {
        return creoContentArrayList;
    }

    public void setCreoContentArrayList(ArrayList<CreoContent> creoContentArrayList) {
        this.creoContentArrayList = creoContentArrayList;
    }
}
