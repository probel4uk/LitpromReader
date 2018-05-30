package com.example.litpromreader.model;

import java.io.Serializable;

/**
 * Created by Андрей on 14.11.2017.
 */

public class LitpomPresText implements  Serializable {
    private Author autor;
    private LitpromSection litpromSection;
    private LitpromCreotive litpromCreotive;

    public LitpomPresText(Author autor, LitpromSection litpromSection, LitpromCreotive litpromCreotive) {
        this.autor = autor;
        this.litpromSection = litpromSection;
        this.litpromCreotive = litpromCreotive;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public LitpromSection getLitpromSection() {
        return litpromSection;
    }

    public void setLitpromSection(LitpromSection litpromSection) {
        this.litpromSection = litpromSection;
    }

    public LitpromCreotive getLitpromCreotive() {
        return litpromCreotive;
    }

    public void setLitpromCreotive(LitpromCreotive litpromCreotive) {
        this.litpromCreotive = litpromCreotive;
    }


}
