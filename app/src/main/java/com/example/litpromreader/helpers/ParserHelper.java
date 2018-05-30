package com.example.litpromreader.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoString;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Андрей on 20.11.2017.
 */

public class ParserHelper {
    private ParserHelper(){

    }
    public static String getClearString(String string){
        return string.replaceAll("[\\:\\[\\]]", "").trim();
    }
    public static Bitmap getImageBitMap(String imageUrl){
        try{
            HttpURLConnection connection = (HttpURLConnection)new URL(imageUrl).openConnection();

            connection.connect();

            InputStream input= connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);

            input.close();
            connection.disconnect();
            return bitmap;

        }
        catch (Exception f) {
            // TODO Auto-generated catch block
            f.printStackTrace();
        }
        return null;
    }
    public static void splitHtmlSring(String newElementString, ArrayList<CreoContent> arrayList) {
        String[] split = newElementString.split("(<br>)+?");
        for (String str :split) {
            str = str.trim();
            if(!str.equals("")){
                arrayList.add(new CreoString(str));

            }

        }
    }
}
