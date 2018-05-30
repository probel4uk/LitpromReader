package com.example.litpromreader.parser;

import android.os.AsyncTask;

import com.example.litpromreader.helpers.ParserHelper;
import com.example.litpromreader.model.StartPage;
import com.example.litpromreader.model.creoContent.CreoContent;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Грыбочак on 13.12.2017.
 */

public class ParseStartPage extends AsyncTask<Void, Void, StartPage> {

    @Override
    protected StartPage doInBackground(Void... voids) {
        ArrayList<CreoContent> creoContentsArrayList = new ArrayList<>();

        Document document;
        try{
            Connection connection = Jsoup.connect("http://litprom.ru/");
            document = connection.get();
            Element body = document.body();
            Element centerCol = body.select(".centerCol").first();
            Element tbody = centerCol.select("tbody").first();
            Elements trs = tbody.select("tr");
            for(Element tr: trs){
                Elements trAllElements = tr.getAllElements();
                for(Element trElement:trAllElements){
                    String htmlString = trElement.ownText();
                    ParserHelper.splitHtmlSring(htmlString, creoContentsArrayList);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new StartPage(creoContentsArrayList);
    }
}
