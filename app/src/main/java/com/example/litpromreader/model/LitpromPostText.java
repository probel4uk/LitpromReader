package com.example.litpromreader.model;

import com.example.litpromreader.model.creoContent.CreoContent;

import java.util.ArrayList;

/**
 * Created by Андрей on 20.11.2017.
 */

public class LitpromPostText extends LitpomPresText {
    private ArrayList<CreoContent> creoContentArrayList;
    private ArrayList<Comment> comments;

    public LitpromPostText(Author autor, LitpromSection litpromSection, LitpromCreotive litpromCreotive, ArrayList<CreoContent> creoContentArrayList, ArrayList<Comment> comments) {
        super(autor, litpromSection, litpromCreotive);
        this.creoContentArrayList = creoContentArrayList;
        this.comments = comments;
    }

    public ArrayList<CreoContent> getCreoContentArrayList() {
        return creoContentArrayList;
    }

    public void setCreoContentArrayList(ArrayList<CreoContent> creoContentArrayList) {
        this.creoContentArrayList = creoContentArrayList;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
