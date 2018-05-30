package com.example.litpromreader.model;

import java.io.Serializable;

/**
 * Created by Грыбочак on 12.11.2017.
 */

public abstract class LitpromPage implements Serializable {
    private String name;
    private String url;

    public LitpromPage(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
