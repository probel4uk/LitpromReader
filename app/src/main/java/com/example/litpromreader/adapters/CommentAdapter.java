package com.example.litpromreader.adapters;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.litpromreader.R;
import com.example.litpromreader.model.Comment;
import com.example.litpromreader.model.LitpromPostText;
import com.example.litpromreader.model.creoContent.CreoContent;
import com.example.litpromreader.model.creoContent.CreoString;

import java.util.ArrayList;

/**
 * Created by Грыбочак on 27.11.2017.
 */

public class CommentAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    LitpromPostText litpromPostText;
    ArrayList<Comment> commentArrayList;
    int textSize;

    public CommentAdapter(Context context, LitpromPostText litpromPostText, int textSize) {
        this.context = context;
        this.litpromPostText = litpromPostText;

        commentArrayList = litpromPostText.getComments();

        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.textSize = textSize;
    }

    @Override
    public int getCount() {
        return commentArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.comment_item, parent, false);
         Comment comment = commentArrayList.get(position);
        ((TextView)view.findViewById(R.id.commentNumTextView)).setText(String.valueOf(position));
        ((TextView)view.findViewById(R.id.commentAuthorTextView)).setText(comment.getAuthorComment().getName());



        LinearLayout commentContenLayout = (LinearLayout) view.findViewById(R.id.commentContentLayout) ;
        ArrayList<CreoContent> commentContentArrayList = comment.getCommentContentArrayList();

        for(CreoContent creoContent2: commentContentArrayList){
            if(creoContent2 instanceof CreoString){
                CreoString creoString = (CreoString)creoContent2;

//                View innerView = layoutInflater.inflate(R.layout.creo_paragraph, parent, false);
//                TextView innerTextView =(TextView)innerView.findViewById(R.id.paragraphTextView);
//                innerTextView.setText("\t" + creoString.getString());
//                commentContenLayout.addView(innerTextView);


                TextView textView = new TextView(context);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
                textView.setText("\t" + creoString.getString());
                commentContenLayout.addView(textView);

            }

        }


        return view;
    }
}
