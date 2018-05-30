package com.example.litpromreader.model.creoContent;

/**
 * Created by Грыбочак on 24.11.2017.
 */
public class CreoString implements CreoContent {
    private String string;

    public CreoString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
