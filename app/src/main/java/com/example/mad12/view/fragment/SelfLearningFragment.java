package com.example.mad12.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mad12.R;
import com.example.mad12.utils.Session;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelfLearningFragment extends Fragment {
    TextView txtName;
    public SelfLearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_self_learning,container,false);
        initView(view);
        getNameUser();
        return view;
    }

    //Đọc thôgn tin name trong preference và set cho textview
    private void getNameUser() {
        txtName.setText(Session.read(getContext(),"personName",""));
    }

    private void initView(View view) {
        txtName=(TextView) view.findViewById(R.id.textview_selflearningfragment_name);
    }
}
