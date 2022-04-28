package com.upn.ferniandita.mydietdiary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Stefanus on 31/05/2017.
 */

public class AccountFragment extends Fragment {

    DataHelper dbHelper;
    Cursor cursor;

    Button btn_save;
    EditText et_email, et_username;
    TextInputEditText et_oldPassword, et_newPassword, et_confirmPassword;
    String password = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_account, container, false);

        dbHelper = new DataHelper(mView.getContext());
        btn_save = (Button) mView.findViewById(R.id.btn_save);
        et_email = (EditText) mView.findViewById(R.id.et_email);
        et_username = (EditText) mView.findViewById(R.id.et_username);
        et_oldPassword = (TextInputEditText) mView.findViewById(R.id.et_oldPassword);
        et_newPassword = (TextInputEditText) mView.findViewById(R.id.et_newPassword);
        et_confirmPassword = (TextInputEditText) mView.findViewById(R.id.et_confirmPassword);

        getData();

        et_email.setEnabled(false);
        et_username.setEnabled(false);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pword = et_oldPassword.getText().toString();
                String npword = et_newPassword.getText().toString();
                String cpword = et_confirmPassword.getText().toString();
                if(pword.equals(password) && npword.equals(cpword)){
                    changePassword(npword);
                    password = npword;
                    et_oldPassword.setText("");
                    et_newPassword.setText("");
                    et_confirmPassword.setText("");
                    Toast.makeText(getContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mView;
    }

    public void getData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String uname = getActivity().getIntent().getStringExtra("username");
        cursor = db.rawQuery("select email,username,password from user where username='" + uname + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            et_email.setText(cursor.getString(0));
            et_username.setText(cursor.getString(1));
            password = cursor.getString(2);
        }
        db.close();
    }

    public void changePassword(String npword){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String uname = getActivity().getIntent().getStringExtra("username");
        db.execSQL("update user set password='"+npword+"' where username='"+uname+"'");
        db.close();
    }
}
