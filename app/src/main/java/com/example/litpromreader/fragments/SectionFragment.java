package com.example.litpromreader.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.litpromreader.R;
import com.example.litpromreader.adapters.ListSectionAdapter;
import com.example.litpromreader.helpers.ErrorShowHelper;
import com.example.litpromreader.model.LitpromSection;
import com.example.litpromreader.parser.ParseSection;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<LitpromSection> litpromSections = new ArrayList<>();
    private ListView listView;



    public SectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionFragment newInstance(String param1, String param2) {
        SectionFragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        ParseSection parseTitle = new ParseSection();
        parseTitle.execute();
        try {
            litpromSections=parseTitle.get();
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
        if(litpromSections != null && litpromSections.size() > 0){
            View rootVew = inflater.inflate(R.layout.fragment_section, container, false);
            ListView listView = (ListView) rootVew.findViewById(R.id.sectionContent);

            ListSectionAdapter listSectionAdapter = new ListSectionAdapter(getActivity(), litpromSections);
            listView.setAdapter(listSectionAdapter);

            return rootVew;
        }else {

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
    @Override
    public void onStart() {
        super.onStart();


    }



}
