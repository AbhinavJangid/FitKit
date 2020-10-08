package com.example.hp.fitkit;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BmiFragment extends Fragment {
    TextView tv, category,ht,wt;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    int weightL, heightL;
    double bmi_results ;

    public BmiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bmi, null);
        sp = getActivity().getSharedPreferences("My Pref", Context.MODE_PRIVATE);
        tv = rootView.findViewById(R.id.bmi_tv);
        category = rootView.findViewById(R.id.category_tv);
        ht = rootView.findViewById(R.id.height_tv);
        wt = rootView.findViewById(R.id.weight_tv);
        String weight = sp.getString("weight", "");
        wt.setText(weight);
        String height = sp.getString("height", "");
        ht.setText(height);
        if (weight.isEmpty() && height.isEmpty()) {
            Toast.makeText(getActivity(), "enter valid", Toast.LENGTH_SHORT).show();
        } else {
            weightL = Integer.parseInt(weight);
            heightL = Integer.parseInt(height);
        }
        bmi_results = (weightL * 10000) / (heightL * heightL);
        String sol = Double.toString(bmi_results);
          tv.setText(""+sol);

       int bmiIndex = (int) Math.floor(bmi_results);
        if (bmiIndex < 18 && bmiIndex > 0) {
            category.setText("UNDER WEIGHT");
        } else if (bmiIndex >= 18 && bmiIndex <= 25) {
            category.setText("NORMAL");
        } else if (bmiIndex > 25 && bmiIndex < 30) {
            category.setText("OVERWEIGHT");
        } else {
            category.setText("OBESE");
        }

        GradientDrawable bmiColor = (GradientDrawable) category.getBackground();
        int magnitudeColor = getMagnitudeColor(bmi_results);
        bmiColor.setColor(magnitudeColor);

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                magnitudeColorResourceId = R.color.bmi17;
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24: magnitudeColorResourceId = R.color.bmi19;
                break;
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                magnitudeColorResourceId = R.color.bmi29;
                break;
            default:
                magnitudeColorResourceId = R.color.bmi30plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
