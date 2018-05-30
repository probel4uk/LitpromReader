package com.example.litpromreader.parser;

import android.os.AsyncTask;

import com.example.litpromreader.helpers.ParserHelper;
import com.example.litpromreader.model.Author;
import com.example.litpromreader.model.LitpromCreotive;
import com.example.litpromreader.model.Pulse;
import com.example.litpromreader.model.creoContent.CreoContent;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Грыбочак on 25.12.2017.
 */

public class ParsePulse extends AsyncTask<Void, Void, ArrayList<Pulse>> {
    @Override
    protected ArrayList<Pulse> doInBackground(Void... voids) {
        ArrayList<Pulse> litpromPulses = new ArrayList<>();
        Document document=null;
        try {
            Connection connection = Jsoup.connect("http://litprom.ru/pulse.html");
            document = connection.get();
            Element body = document.body();
            Element newElement = body.select(".centerCol").first().select(".new").first();
            Elements comments = body.select(".comment");

            for(Element comment: comments){
                Element zagElement = comment.select(".zag").first();
                Element creoElement = zagElement.select("a[href]").first();
                String creoName = creoElement.text();
                String creoUrl = creoElement.attr("abs:href");

                Element contentDiv = comment.select("div").get(2);
                Element commentAuthorElement = contentDiv.select("a[href]").first();
                String commentAuthorName = commentAuthorElement.text();
                String commentAuthorUrl = commentAuthorElement.attr("abs:href");

                ArrayList<CreoContent> creoContentArrayList = new ArrayList<>();
                ParserHelper.splitHtmlSring(ParserHelper.getClearString(contentDiv.ownText()), creoContentArrayList);





                litpromPulses.add(new Pulse(new Author(commentAuthorName, commentAuthorUrl),
                        creoContentArrayList,
                        new LitpromCreotive(creoName, creoUrl)));

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return litpromPulses;
    }

}
