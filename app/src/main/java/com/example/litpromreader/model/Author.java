package com.example.litpromreader.model;

import android.graphics.Bitmap;

/**
 * Created by Грыбочак on 12.11.2017.
 */


public class Author extends LitpromPage {
    private Bitmap imageBitmap;


    public Author(String name, String url) {
        super(name, url);
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public Author(String name, String url, Bitmap imageUrl) {
        super(name, url);
        this.imageBitmap = imageUrl;
    }
}
