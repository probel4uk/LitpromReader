package com.example.litpromreader.model;

import com.example.litpromreader.model.creoContent.CreoContent;

import java.util.ArrayList;

/**
 * Created by Андрей on 16.11.2017.
 */

public class Comment {
    private Author authorComment;
    private ArrayList<CreoContent> commentContentArrayList;

    public Comment(Author authorCommentName, ArrayList<CreoContent> commentContentArrayList) {
        this.authorComment = authorCommentName;
        this.commentContentArrayList = commentContentArrayList;
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
}
