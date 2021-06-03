package com.example.mad12.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mad12.R;
import com.example.mad12.utils.Session;
import com.example.mad12.view.activity.ListExamOnlineActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelfLearningFragment extends Fragment {
    TextView txtName, txtTestOnline;
    public SelfLearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_self_learning,container,false);
        initView(view);
        //Đọc thôgn tin name trong preference và set cho textview
        getNameUser();
        //Event go to test online
        txtTestOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ListExamOnlineActivity.class));
            }
        });
        return view;
    }

    //Đọc thôgn tin name trong preference và set cho textview
    private void getNameUser() {
        txtName.setText(Session.read(getContext(),"personName",""));
    }

    private void initView(View view) {
        txtName=(TextView) view.findViewById(R.id.textview_selflearningfragment_name);
        txtTestOnline = (TextView) view.findViewById(R.id.textview_selflearningfragment_icon1);
    }
}
