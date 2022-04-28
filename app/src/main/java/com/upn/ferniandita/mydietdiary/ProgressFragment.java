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
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class ProgressFragment extends Fragment {

    DataHelper dbHelper;
    Cursor cursor;

    Button btn_insert;
    EditText et_weight;
    LineChart lineChart;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_progress, container, false);

        dbHelper = new DataHelper(getContext());

        btn_insert = (Button) mView.findViewById(R.id.btn_insert);
        et_weight = (EditText) mView.findViewById(R.id.et_weight);
        lineChart = (LineChart) mView.findViewById(R.id.chart);

        refreshChart();

        lineChart.animateY(5000);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = et_weight.getText().toString();
                insertWeight(weight);
                refreshChart();
                et_weight.setText("");
            }
        });

        return mView;
    }

    public void refreshChart() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        String uname = getActivity().getIntent().getStringExtra("username");
        int i = 0;


        cursor = db.rawQuery("select weight from weightuser where username='" + uname + "'", null);
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            float weight = Float.parseFloat(cursor.getString(0));
            entries.add(new Entry(weight, cc));
            labels.add(String.valueOf(cc + 1));
        }
        LineDataSet dataset = new LineDataSet(entries, "# of Insert");
        //dataset.setDrawFilled(true);
        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        db.close();
    }

    public void insertWeight(String weight) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String uname = getActivity().getIntent().getStringExtra("username");
        db.execSQL("insert into weightuser (username,weight) values ('" + uname + "','" + weight + "')");
        db.execSQL("update diagnose set weight='" + weight + "' where username ='" + uname + "'");
        db.close();
    }
}
