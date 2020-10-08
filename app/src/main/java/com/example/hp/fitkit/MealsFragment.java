package com.example.hp.fitkit;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class MealsFragment extends Fragment {
    TextView tv;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    int weight,height;
    public MealsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meals,null);

        ExpandableTextView expTv1 =  rootView.findViewById(R.id.data);

        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expTv1.setText(getString(R.string.dummy_text1));


        return rootView;
    }
}
