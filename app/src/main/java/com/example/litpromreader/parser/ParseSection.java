package com.example.litpromreader.parser;

import android.os.AsyncTask;

import com.example.litpromreader.exception.MyException;
import com.example.litpromreader.model.LitpromSection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Андрей on 13.11.2017.
 */

public class ParseSection extends AsyncTask<Void, Void, ArrayList<LitpromSection>> {
    @Override
    protected ArrayList<LitpromSection> doInBackground(Void... voids) {
        ArrayList<LitpromSection> litpromSections = new ArrayList<>();
        Document document=null;
        try {
            Connection connection = Jsoup.connect("http://litprom.ru/");
            document = connection.get();
            Elements leftMenuElement = document.select(".leftmenu");
            Elements selectElements = leftMenuElement.get(1).select("a[href]");

            for(Element seElement: selectElements){
                litpromSections.add(new LitpromSection(seElement.text(), seElement.attr("abs:href")));
            }

//            for(Element entryElement: leftMenuElement){
//                Elements selectElements = entryElement.select("a[href]");
//                for(Element seElement: selectElements){
//                    litpromSections.add(new LitpromSection(seElement.text(), seElement.attr("abs:href")));
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return litpromSections;
    }
}
