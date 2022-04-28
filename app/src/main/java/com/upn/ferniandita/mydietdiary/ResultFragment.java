package com.upn.ferniandita.mydietdiary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class ResultFragment extends Fragment {

    DataHelper dbHelper;
    Cursor cursor;
    String uname;
    TextView tv_weight, tv_height, tv_age, tv_gender, tv_bloodType, tv_medicalHistory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_result, container, false);
        dbHelper = new DataHelper(mView.getContext());

        tv_weight = (TextView) mView.findViewById(R.id.tv_weight);
        tv_height = (TextView) mView.findViewById(R.id.tv_height);
        tv_age = (TextView) mView.findViewById(R.id.tv_age);
        tv_gender = (TextView) mView.findViewById(R.id.tv_gender);
        tv_bloodType = (TextView) mView.findViewById(R.id.tv_bloodType);
        tv_medicalHistory = (TextView) mView.findViewById(R.id.tv_medicalHistory);

        getResult();

        return mView;
    }

    public void getResult() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        uname = getActivity().getIntent().getStringExtra("username");
        cursor = db.rawQuery("select user.username, diagnose.weight, diagnose.height," +
                "diagnose.age, diagnose.gender, diagnose.bloodtype,diagnose.maag," +
                "diagnose.hypertension, diagnose.diabetes, diagnose.gastritis, diagnose.cholesterol " +
                "from user inner join diagnose on user.username = diagnose.username where user.username ='" + uname + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String weight = cursor.getString(1) + " kg";
            String height = cursor.getString(2) + " cm";
            String age = cursor.getString(3);
            String gender = cursor.getString(4);
            String bloodtype = cursor.getString(5);
            String medicalhistory = " ";
            if (cursor.getString(6).equals("true")) medicalhistory += "Maag\n";
            if (cursor.getString(7).equals("true")) medicalhistory += "Hypertension\n";
            if (cursor.getString(8).equals("true")) medicalhistory += "Diabetes\n";
            if (cursor.getString(9).equals("true")) medicalhistory += "Gastritis\n";
            if (cursor.getString(10).equals("true")) medicalhistory += "Cholesterol\n";
            tv_weight.setText(weight);
            tv_height.setText(height);
            tv_age.setText(age);
            tv_gender.setText(gender);
            tv_bloodType.setText(bloodtype);
            tv_medicalHistory.setText(medicalhistory);
        }
        db.close();
    }
}
