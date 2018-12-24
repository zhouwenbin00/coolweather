package com.coolweather.app.activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.coolweather.app.R;


public class Fragment3 extends Fragment implements View.OnClickListener{
    private Button btnExit;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnExit= (Button) view.findViewById(R.id.btn_exit);
        btn1= (Button) view.findViewById(R.id.button1);
        btn2= (Button) view.findViewById(R.id.button2);
        btn3= (Button) view.findViewById(R.id.button3);
        btnExit.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit:
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.button1 :
            case R.id.button2:
            case R.id.button3:
                Toast.makeText(getActivity().getApplicationContext(),"功能开发中",Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }
}
