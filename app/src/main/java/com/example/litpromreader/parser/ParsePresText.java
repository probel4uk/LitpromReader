package com.example.litpromreader.parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.litpromreader.helpers.ParserHelper;
import com.example.litpromreader.model.Author;
import com.example.litpromreader.model.LitpomPresText;
import com.example.litpromreader.model.LitpromCreotive;
import com.example.litpromreader.model.LitpromSection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Андрей on 14.11.2017.
 */

public class ParsePresText extends AsyncTask<Void, Void, ArrayList<LitpomPresText>> {
    private String sectionUrl;
    public ParsePresText(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }
    public ParsePresText() {
        this.sectionUrl = "http://litprom.ru/list.html";
    }

    @Override
    protected ArrayList<LitpomPresText> doInBackground(Void... voids) {
        ArrayList<LitpomPresText> litpomPresTexts = new ArrayList<>();
        Document document=null;
        try {
            Connection connection = Jsoup.connect(sectionUrl);
            document = connection.get();
            Element body = document.body();
            Elements newTextElements = body.select(".centerCol").select(".new");
            Elements storyElements = newTextElements.first().select(".story");
            for(Element storyElement: storyElements){
                //тут надо брать урл картинки до блока  с хеадар3

                Element image = storyElement.select("img.avatar").first();
                String imageUrl = image.attr("abs:src") ;



                Bitmap imageBitmap = ParserHelper.getImageBitMap(imageUrl);

                Element header3Element = storyElement.select(".header3").first();

                Element autorElement = header3Element.select(".author").first().select("a[href]").first();
                Author autor = new Author(autorElement.text(),
                        autorElement.attr("abs:href"),
                        imageBitmap);




                Element titleElement = header3Element.select(".title").first();

                Element creotiveElement = titleElement.select("a[href]").first();

                LitpromCreotive litpromCreotive = new LitpromCreotive(creotiveElement.text(),
                        creotiveElement.attr("abs:href"));


                LitpromSection litpromSection = new LitpromSection(ParserHelper.getClearString(header3Element.ownText()),
                        "empty");

                litpomPresTexts.add(new LitpomPresText(autor, litpromSection, litpromCreotive));




            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        for (int i = 0; i < 15; i++) {
//            litpomPresTexts.add(new LitpomPresText(new Author("fddsfds", "dsfds"),
//                    new LitpromSection("sdfsdf", "dsfsdf"),
//                    new LitpromCreotive("asdasd", "sdfsfds")));
//        }
        return litpomPresTexts;
    }


}
