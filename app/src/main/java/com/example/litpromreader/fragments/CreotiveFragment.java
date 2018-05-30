package com.example.litpromreader.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.litpromreader.R;
import com.example.litpromreader.adapters.CommentAdapter;
import com.example.litpromreader.adapters.CreoContentAdapter;
import com.example.litpromreader.constant.TextSizeConstant;
import com.example.litpromreader.helpers.ErrorShowHelper;
import com.example.litpromreader.model.LitpomPresText;
import com.example.litpromreader.model.LitpromPostText;
import com.example.litpromreader.parser.ParsePostText;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreotiveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreotiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreotiveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView creoContentListView;
    private TextView comentChangerView;
    private ListView commentsListVew;

    private LitpromPostText litpromPostText;

    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TEXT_SIZE = "textsize";

    private int textSize;



    private OnFragmentInteractionListener mListener;

    public CreotiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreotiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreotiveFragment newInstance(String param1, String param2) {
        CreotiveFragment fragment = new CreotiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String creoUrl = getArguments().getString("creoUrl");

        ParsePostText parsePostText = new ParsePostText(creoUrl);
        parsePostText.execute();
        try {
            litpromPostText = parsePostText.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (litpromPostText !=null) {
            // Inflate the layout for this fragment

            View rootVew = inflater.inflate(R.layout.fragment_creotive, container, false);
            creoContentListView =(ListView)rootVew.findViewById(R.id.croContentListView);
            commentsListVew =(ListView)rootVew.findViewById(R.id.commentsListView);
            comentChangerView =(TextView) rootVew.findViewById(R.id.comentChangerView);

            mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            if (mSettings.contains(APP_PREFERENCES_TEXT_SIZE)){
                textSize =mSettings.getInt(APP_PREFERENCES_TEXT_SIZE, 0);
            }else {
                textSize = TextSizeConstant.defTextSize;
            }




            creoContentListView.setAdapter(new CreoContentAdapter(getActivity(),litpromPostText, textSize));
            commentsListVew.setAdapter(new CommentAdapter(getActivity(), litpromPostText, textSize));


            //setCreotiveView(rootVew, litpromPostText);

            creoContentListView.setDivider(null);


            commentsListVew.setVisibility(View.INVISIBLE);


            comentChangerView.setOnClickListener(getViewChengerListener());


            return rootVew;
        } else {
            return ErrorShowHelper.showErrorView(inflater, container);
        }
    }







    private View.OnClickListener getViewChengerListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(creoContentListView.getVisibility() != View.VISIBLE){
                    creoContentListView.setVisibility(View.VISIBLE);
                    commentsListVew.setVisibility(View.GONE);
                    comentChangerView.setText("Комментарии");
                }else {
                    creoContentListView.setVisibility(View.GONE);
                    commentsListVew.setVisibility(View.VISIBLE);

                    comentChangerView.setText("Креотив");

                }
            }
        };
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
