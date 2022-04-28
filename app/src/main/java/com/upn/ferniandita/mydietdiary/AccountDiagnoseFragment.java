package com.upn.ferniandita.mydietdiary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Stefanus on 31/05/2017.
 */

public class AccountDiagnoseFragment extends Fragment {

    DataHelper dbHelper;
    Cursor cursor;

    Button btn_save;
    EditText et_weight, et_height, et_age;
    RadioButton rb_male, rb_female;
    RadioButton rb_golA, rb_golB, rb_golAB, rb_golO;
    CheckBox cb_maag, cb_hypertension, cb_diabetes, cb_gastritis, cb_cholesterol;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_diagnose, container, false);

        dbHelper = new DataHelper(getContext());
        et_weight = (EditText) mView.findViewById(R.id.et_weight);
        et_height = (EditText) mView.findViewById(R.id.et_height);
        et_age = (EditText) mView.findViewById(R.id.et_age);
        rb_male = (RadioButton) mView.findViewById(R.id.rb_male);
        rb_female = (RadioButton) mView.findViewById(R.id.rb_female);
        rb_golA = (RadioButton) mView.findViewById(R.id.rb_golA);
        rb_golB = (RadioButton) mView.findViewById(R.id.rb_golB);
        rb_golAB = (RadioButton) mView.findViewById(R.id.rb_golAB);
        rb_golO = (RadioButton) mView.findViewById(R.id.rb_golO);
        cb_maag = (CheckBox) mView.findViewById(R.id.cb_maag);
        cb_hypertension = (CheckBox) mView.findViewById(R.id.cb_hypertension);
        cb_diabetes = (CheckBox) mView.findViewById(R.id.cb_diabetes);
        cb_gastritis = (CheckBox) mView.findViewById(R.id.cb_gastritis);
        cb_cholesterol = (CheckBox) mView.findViewById(R.id.cb_cholesterol);
        btn_save = (Button) mView.findViewById(R.id.btn_save);

        setData();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = getActivity().getIntent().getStringExtra("username");
                String weight = et_weight.getText().toString();
                String height = et_height.getText().toString();
                String age = et_age.getText().toString();
                String gender = getGender();
                String bloodtype = getBloodType();
                Boolean maag = cb_maag.isChecked();
                Boolean hypertension = cb_hypertension.isChecked();
                Boolean diabetes = cb_diabetes.isChecked();
                Boolean gastritis = cb_gastritis.isChecked();
                Boolean cholesterol = cb_cholesterol.isChecked();
                updateData(uname, weight, height, age, gender, bloodtype, maag, hypertension, diabetes, gastritis, cholesterol);
                Toast.makeText(getContext(),"Data Saved",Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }

    public void setData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String uname = getActivity().getIntent().getStringExtra("username");
        cursor = db.rawQuery("select weight,height,age,gender,bloodtype," +
                "maag,hypertension,diabetes,gastritis,cholesterol from diagnose where username='" + uname + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            et_weight.setText(cursor.getString(0));
            et_height.setText(cursor.getString(1));
            et_age.setText(cursor.getString(2));

            if (cursor.getString(3).equals("Male")) {
                rb_male.setChecked(true);
            } else if (cursor.getString(3).equals("Female")) {
                rb_female.setChecked(true);
            }

            if (cursor.getString(4).equals("A")) {
                rb_golA.setChecked(true);
            } else if (cursor.getString(4).equals("B")) {
                rb_golB.setChecked(true);
            } else if (cursor.getString(4).equals("AB")) {
                rb_golAB.setChecked(true);
            } else if (cursor.getString(4).equals("O")) {
                rb_golO.setChecked(true);
            }

            if (cursor.getString(5).equals("true")) {
                cb_maag.setChecked(true);
            }
            if (cursor.getString(6).equals("true")) {
                cb_hypertension.setChecked(true);
            }
            if (cursor.getString(7).equals("true")) {
                cb_diabetes.setChecked(true);
            }
            if (cursor.getString(8).equals("true")) {
                cb_gastritis.setChecked(true);
            }
            if (cursor.getString(9).equals("true")) {
                cb_cholesterol.setChecked(true);
            }
        }
    }

    public String getGender() {
        if (rb_male.isChecked()) return "Male";
        else if (rb_female.isChecked()) return "Female";
        return "";
    }

    public String getBloodType() {
        if (rb_golA.isChecked()) return "A";
        else if (rb_golB.isChecked()) return "B";
        else if (rb_golAB.isChecked()) return "AB";
        else if (rb_golO.isChecked()) return "O";
        return "";
    }

    public void updateData(String uname, String weight, String height, String age, String gender, String bloodtype,
                           Boolean maag, Boolean hypertension, Boolean diabetes, Boolean gastritis, Boolean cholesterol) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("update diagnose set weight='" + weight + "', height='" + height + "', age='" + age +
                "', gender='" + gender + "', bloodtype='" + bloodtype + "', maag='" + maag +
                "', hypertension='" + hypertension + "', diabetes='" + diabetes + "', gastritis='" + gastritis +
                "', cholesterol='" + cholesterol + "'");
        db.execSQL("insert into weightuser (username,weight) values ('" + uname + "','" + weight + "')");
        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
