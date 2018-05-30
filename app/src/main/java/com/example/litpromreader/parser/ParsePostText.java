package com.example.litpromreader.parser;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;


import com.example.litpromreader.helpers.ParserHelper;
import com.example.litpromreader.model.Author;
import com.example.litpromreader.model.Comment;
import com.example.litpromreader.model.LitpromCreotive;
import com.example.litpromreader.model.LitpromPostText;
import com.example.litpromreader.model.LitpromSection;
import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoInfo;
import com.example.litpromreader.model.creoContent.CreoString;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Андрей on 20.11.2017.
 */

public class ParsePostText extends AsyncTask<Void, Void, LitpromPostText> {




    private String creoUrl;
    public ParsePostText(String creoUrl) {
        this.creoUrl = creoUrl;

    }



    @Override
    protected LitpromPostText doInBackground(Void... voids) {
        LitpromPostText litpromPostText = null;


        Document document;
        try{
            Connection connection = Jsoup.connect(creoUrl);
            document = connection.get();
            Element body = document.body();
            Element centerCol = body.select(".centerCol").first();



            // Starting parse creotive content
            Element newElement = centerCol.select(".new").first();

            String newElementString = newElement.html();




            String sectionName = newElement.select(".header1").select("h1").first().ownText();

            /**
             * Author*/
            Element image = newElement.select("img.avatar").first();
            String imageUrl = image.attr("abs:src") ;
            Bitmap imageAuthorBitmap = ParserHelper.getImageBitMap(imageUrl);

            /**
             * Creotive Name
             * */
            String creoName = newElement.select("h3").first().ownText();


            Element autorElement = newElement.select("a[href]").get(1);
            Author autor = new Author(autorElement.text(),
                    autorElement.attr("abs:href"),
                    imageAuthorBitmap);


            ArrayList<CreoContent> creoContent = new ArrayList<>();

            creoContent.add(new CreoInfo());

            /**
             * Parapraphs*/



            getCreoStrings(newElementString, creoContent);


            // Ending parse creotive content

            //Starting commentParsing

            ArrayList<Comment> comments = new ArrayList<>();
            Element lpCommentElement = centerCol.select("div#lp_comments").first();
            Elements lpComments = lpCommentElement.select(".comment");
            for(Element lpComment: lpComments){
                //Author
                Element zagElement = lpComment.select("div.zag").first();
                Element commentAutorElement = zagElement.select("a[href]").first();
                Author commentAuthor = new Author(commentAutorElement.text(),
                        commentAutorElement.attr("abs:href"));

                //CommentContent
                ArrayList<CreoContent> creoCommentContents = new ArrayList<>();
                String commentContentString = lpComment.select("div").get(2).html();
                ParserHelper.splitHtmlSring(commentContentString, creoCommentContents);
                comments.add(new Comment(commentAuthor, creoCommentContents));



            }

            //Ending Comment Parsing
            litpromPostText = new LitpromPostText(autor,
                    new LitpromSection(sectionName),
                    new LitpromCreotive(creoName),
                    creoContent,
                    comments);



        }  catch (IOException e) {
        e.printStackTrace();
        return null;
    }
        return litpromPostText;
    }

    private void getCreoStrings(String newElementString, ArrayList<CreoContent> creoContent) {
        String regex ="^<div(.|\\n)*?<div class=\"print\">(.|\\n)*?div>";
        newElementString = newElementString.replaceAll(regex, "");
        ParserHelper.splitHtmlSring(newElementString, creoContent);
    }

//    private void splitHtmlSring(String newElementString, ArrayList<CreoContent> arrayList) {
//        String[] split = newElementString.split("(<br>)+?");
//        for (String str :split) {
//            str = str.trim();
//            if(!str.equals("")){
//                arrayList.add(new CreoString(str));
//
//            }
//
//        }
//    }
}
