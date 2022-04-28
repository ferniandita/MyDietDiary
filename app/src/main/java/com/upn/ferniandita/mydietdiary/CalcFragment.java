package com.upn.ferniandita.mydietdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Stefanus on 26/05/2017.
 */

public class CalcFragment extends Fragment {

    TextView yourweight, yourweight2, yourheight, yourheight2, yourbmi, yourbmi2, yourcategory, yourcategory2, yourideal, yourideal2;
    Button btn_check;
    EditText et_berat, et_tinggi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_calc, container, false);

        yourweight = (TextView) mView.findViewById(R.id.yourweight);
        yourweight2 = (TextView) mView.findViewById(R.id.yourweight2);
        yourheight = (TextView) mView.findViewById(R.id.yourheight);
        yourheight2 = (TextView) mView.findViewById(R.id.yourheight2);
        yourbmi = (TextView) mView.findViewById(R.id.yourbmi);
        yourbmi2 = (TextView) mView.findViewById(R.id.yourbmi2);
        yourcategory = (TextView) mView.findViewById(R.id.yourcategory);
        yourcategory2 = (TextView) mView.findViewById(R.id.yourcategory2);
        yourideal = (TextView) mView.findViewById(R.id.yourideal);
        yourideal2 = (TextView) mView.findViewById(R.id.yourideal2);
        et_berat = (EditText) mView.findViewById(R.id.et_berat);
        et_tinggi = (EditText) mView.findViewById(R.id.et_tinggi);
        btn_check = (Button) mView.findViewById(R.id.btn_check);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float berat = Float.parseFloat(et_berat.getText().toString());
                    float tinggi = Float.parseFloat(et_tinggi.getText().toString());
                    float bmi = hitungBMI(berat, tinggi);
                    float ideal = hitungIdeal(tinggi);

                    yourweight.setText("Weight");
                    yourweight2.setText(": " + String.valueOf(berat));
                    yourheight.setText("Height");
                    yourheight2.setText(": " + String.valueOf(tinggi));
                    yourbmi.setText("Body Mass Index");
                    yourbmi2.setText(": " + String.valueOf(bmi) + " kg/m2");
                    yourcategory.setText("Category");
                    yourcategory2.setText(": " + kategoriBMI(bmi));
                    yourideal.setText("Ideal Weight");
                    yourideal2.setText(": " + String.valueOf(ideal) + " kg");
                }catch (Exception e){
                    Toast.makeText(getContext(),"Input Your Data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }

    public float hitungBMI(float berat, float tinggi) {

        float hasil;
        hasil = berat / ((tinggi * tinggi) / 10000);
        return hasil;
    }

    public float hitungIdeal(float tinggi) {

        float hasil2;
        hasil2 = (float) ((tinggi-100) * 0.90);
        return hasil2;
    }

    public String kategoriBMI(float bmi) {

        String hasil;

        if (bmi <= 18.5) hasil = "Underweight";
        else if (bmi < 25) hasil = "Normal";
        else if (bmi < 30) hasil = "Overweight";
        else hasil = "Obese";

        return hasil;
    }
}
