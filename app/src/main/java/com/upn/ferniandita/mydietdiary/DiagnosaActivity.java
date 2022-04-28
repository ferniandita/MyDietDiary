package com.upn.ferniandita.mydietdiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DiagnosaActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Button btn_finish;
    EditText et_weight, et_height, et_age;
    RadioButton rb_male, rb_female;
    RadioButton rb_golA, rb_golB, rb_golAB, rb_golO;
    CheckBox cb_maag, cb_hypertension, cb_diabetes, cb_gastritis, cb_cholesterol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        dbHelper = new DataHelper(this);
        et_weight = (EditText) findViewById(R.id.et_weight);
        et_height = (EditText) findViewById(R.id.et_height);
        et_age = (EditText) findViewById(R.id.et_age);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        rb_golA = (RadioButton) findViewById(R.id.rb_golA);
        rb_golB = (RadioButton) findViewById(R.id.rb_golB);
        rb_golAB = (RadioButton) findViewById(R.id.rb_golAB);
        rb_golO = (RadioButton) findViewById(R.id.rb_golO);
        cb_maag = (CheckBox) findViewById(R.id.cb_maag);
        cb_hypertension = (CheckBox) findViewById(R.id.cb_hypertension);
        cb_diabetes = (CheckBox) findViewById(R.id.cb_diabetes);
        cb_gastritis = (CheckBox) findViewById(R.id.cb_gastritis);
        cb_cholesterol = (CheckBox) findViewById(R.id.cb_cholesterol);
        btn_finish = (Button) findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = getIntent().getStringExtra("username");
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
                simpanDataTambahan(uname, weight, height, age, gender, bloodtype, maag, hypertension, diabetes, gastritis, cholesterol);
                Intent i = new Intent(DiagnosaActivity.this, MainActivity.class);
                i.putExtra("username",uname);
                startActivity(i);
                finish();
            }
        });
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

    public void simpanDataTambahan(String uname, String weight, String height, String age, String gender, String bloodtype,
                                   Boolean maag, Boolean hypertension, Boolean diabetes, Boolean gastritis, Boolean cholesterol) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into diagnose (username,weight,height,age,gender,bloodtype," +
                "maag,hypertension,diabetes,gastritis,cholesterol) values('" +
                uname + "','" + weight + "','" + height + "','" + age + "','" + gender + "','" + bloodtype + "','" +
                maag + "','" + hypertension + "','" + diabetes + "','" + gastritis + "','" + cholesterol + "');");
        db.execSQL("insert into weightuser (username,weight) values ('"+uname+"','"+weight+"')");
        Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_LONG).show();
        db.close();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DiagnosaActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }
}
