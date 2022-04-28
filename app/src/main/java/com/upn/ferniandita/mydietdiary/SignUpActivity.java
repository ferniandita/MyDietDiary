package com.upn.ferniandita.mydietdiary;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

public class SignUpActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Button btn_signUp1, btnLinkToLogin;
    EditText et_username1, et_fullname1, et_email1, et_password1, et_confirmPassword1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DataHelper(this);
        et_username1 = (EditText)findViewById(R.id.et_username1);
        et_fullname1 = (EditText)findViewById(R.id.et_fullname1);
        et_email1 = (EditText)findViewById(R.id.et_email1);
        et_password1 = (EditText)findViewById(R.id.et_password1);
        et_confirmPassword1 = (EditText)findViewById(R.id.et_confirmPassword1);
        btn_signUp1 = (Button)findViewById(R.id.btn_signUp1);
        btnLinkToLogin = (Button)findViewById(R.id.btnLinkToLoginScreen);

        btn_signUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = et_username1.getText().toString();
                String fullname = et_fullname1.getText().toString();
                String email = et_email1.getText().toString();
                String password = et_password1.getText().toString();
                String conPassword = et_confirmPassword1.getText().toString();
                if(uname.isEmpty() && fullname.isEmpty() && email.isEmpty())
                {
                    Snackbar.make(v, "Register Failed", Snackbar.LENGTH_LONG).show();
                }
                else if(password.equals(conPassword)) {
                    simpanDataUser(uname, password, email, fullname);
                    Intent i = new Intent(SignUpActivity.this,DiagnosaActivity.class);
                    i.putExtra("username",uname);
                    startActivity(i);
                    finish();
                }
                else{
                    Snackbar.make(v,"Password Does Not Matches",Snackbar.LENGTH_LONG).show();
                }

            }
        });

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void simpanDataUser(String uname,String password,String email,String fullname){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into user (username,password,email,fullname,'flag') values ('"
                +uname+"','"+password+"','"+email+"','"+fullname+"','1')");
        Toast.makeText(getApplicationContext(),"Account Saved",Toast.LENGTH_LONG).show();
        db.close();
    }
}
