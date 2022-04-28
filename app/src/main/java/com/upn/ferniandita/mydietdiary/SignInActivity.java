package com.upn.ferniandita.mydietdiary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class SignInActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Cursor cursor;
    Button btn_signIn2, btnLinkToRegister;
    EditText et_username2,et_password2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper = new DataHelper(this);
        et_username2 = (EditText)findViewById(R.id.et_username2);
        et_password2 = (EditText)findViewById(R.id.et_password2);
        btn_signIn2 = (Button)findViewById(R.id.btn_signIn2);
        btnLinkToRegister = (Button)findViewById(R.id.btnLinkToRegisterScreen);

        btn_signIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = et_username2.getText().toString();
                String pword = et_password2.getText().toString();

                if(uname.isEmpty() && pword.isEmpty()) {
                    Snackbar.make(v,"Login Failed",Snackbar.LENGTH_LONG).show();
                }
                else if(cekLogin(uname,pword)){
                    Toast.makeText(v.getContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignInActivity.this,MainActivity.class);
                    i.putExtra("username",uname);
                    startSession(uname);
                    startActivity(i);
                    finish();
                }
                else{
                    Snackbar.make(v,"Wrong Password or Username",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public Boolean cekLogin(String uname,String pword){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select username,password from user where username='"+uname+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            if(uname.equals(cursor.getString(0)) && pword.equals(cursor.getString(1))){
                return true;
            }
        }
        return false;
    }

    public void startSession(String uname){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("update user set flag='1' where username='"+uname+"'");
    }
}
