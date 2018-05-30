package com.example.litpromreader.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.litpromreader.R;
import com.example.litpromreader.adapters.StartPageAdapter;
import com.example.litpromreader.constant.TextSizeConstant;
import com.example.litpromreader.helpers.ErrorShowHelper;
import com.example.litpromreader.model.StartPage;
import com.example.litpromreader.parser.ParseStartPage;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private StartPage startPage;

    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TEXT_SIZE = "textsize";

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseStartPage parseStartPage = new ParseStartPage();
        parseStartPage.execute();
        try {
             startPage = parseStartPage.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        if(startPage != null){
            ListView listViw = (ListView) rootView.findViewById(R.id.stratPageContent);
            View logoImage = inflater.inflate(R.layout.image_logo, listViw, false);
            listViw.addHeaderView(logoImage);

            int textSize;

            mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            if (mSettings.contains(APP_PREFERENCES_TEXT_SIZE)){
                textSize =mSettings.getInt(APP_PREFERENCES_TEXT_SIZE, 0);
            }else {
                textSize = TextSizeConstant.defTextSize;
            }
            listViw.setAdapter(new StartPageAdapter(getActivity(), startPage, textSize));



            return rootView;

        } else {
            return ErrorShowHelper.showErrorView(inflater, container);
        }


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
