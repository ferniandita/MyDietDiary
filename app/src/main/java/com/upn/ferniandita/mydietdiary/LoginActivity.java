package com.upn.ferniandita.mydietdiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class LoginActivity extends AppCompatActivity{

    DataHelper dbHelper;
    Cursor cursor;

    Button btn_signIn,btn_signUp;

    String uname="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DataHelper(this);

        if(!cekSession()){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.putExtra("username",uname);
            startActivity(i);
            finish();
        }

        btn_signIn = (Button)findViewById(R.id.btn_signIn);
        btn_signUp = (Button)findViewById(R.id.btn_signUp);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public Boolean cekSession(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select username from user where flag='1'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            uname = cursor.getString(0);
        }
        db.close();
        return uname.equals("");
    }
}
