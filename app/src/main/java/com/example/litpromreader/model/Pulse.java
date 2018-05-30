package com.example.litpromreader.model;

import com.example.litpromreader.model.Author;
import com.example.litpromreader.model.creoContent.CreoContent;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 25.12.2017.
 */

public class Pulse {
    private Author authorComment;
    private ArrayList<CreoContent> commentContentArrayList;
    private LitpromCreotive litpromCreotive;

    public Pulse(Author authorComment, ArrayList<CreoContent> commentContentArrayList, LitpromCreotive litpromCreotive) {
        this.authorComment = authorComment;
        this.commentContentArrayList = commentContentArrayList;
        this.litpromCreotive = litpromCreotive;
    }

    public Author getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(Author authorComment) {
        this.authorComment = authorComment;
    }

    public ArrayList<CreoContent> getCommentContentArrayList() {
        return commentContentArrayList;
    }

    public void setCommentContentArrayList(ArrayList<CreoContent> commentContentArrayList) {
        this.commentContentArrayList = commentContentArrayList;
    }

    public LitpromCreotive getLitpromCreotive() {
        return litpromCreotive;
    }

    public void setLitpromCreotive(LitpromCreotive litpromCreotive) {
        this.litpromCreotive = litpromCreotive;
    }
}
