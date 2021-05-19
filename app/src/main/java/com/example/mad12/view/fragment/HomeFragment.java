package com.example.mad12.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
public class HomeFragment extends Fragment {
    CardView cvMath,cvPhysic,cvChem,cvLiter,cvGeo,cvEng;
    TextView txtName;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        getNameUser();
        cvMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSelfLearning("Math");
            }
        });
        return view;
    }
    //Đọc thôgn tin name trong preference và set cho textview
    private void getNameUser() {
        txtName.setText(Session.read(getContext(),"personName",""));
    }

    //Chuyển sang màn hình tự học cùng thông tin môn học.
    private void nextSelfLearning(String subject) {


    }

    private void initView(View view) {
        cvMath=(CardView) view.findViewById(R.id.cardview_homefragment_one);
        cvPhysic=(CardView) view.findViewById(R.id.cardview_homefragment_two);
        cvChem=(CardView) view.findViewById(R.id.cardview_homefragment_three);
        cvLiter=(CardView) view.findViewById(R.id.cardview_homefragment_four);
        cvGeo=(CardView) view.findViewById(R.id.cardview_homefragment_five);
        cvEng=(CardView) view.findViewById(R.id.cardview_homefragment_six);
        txtName=(TextView) view.findViewById(R.id.textview_homefragment_name);
    }
}
