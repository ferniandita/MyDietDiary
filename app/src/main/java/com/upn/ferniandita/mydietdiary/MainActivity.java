package com.upn.ferniandita.mydietdiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Ferniandita on 25/04/2017.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataHelper dbHelper;
    Cursor cursor;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    FragmentManager mManager;
    TextView txt_Username, txt_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataHelper(this);
        mManager = getSupportFragmentManager();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        txt_Username = (TextView) header.findViewById(R.id.txt_Username);
        txt_Email = (TextView) header.findViewById(R.id.txt_Email);

        setDataUser();


        mManager.beginTransaction().replace(R.id.cont_Frame, new HomeFragment()).commit();
        setTitle("Home");
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure want to Exit?");
            builder.setCancelable(true);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new HomeFragment()).commit();
            getSupportActionBar().setTitle("Home");
        } else if (id == R.id.nav_result) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new ResultFragment()).commit();
            getSupportActionBar().setTitle("Result");
        } else if (id == R.id.nav_monitor) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new MonitorFragment()).commit();
            getSupportActionBar().setTitle("Monitoring");
        } else if (id == R.id.nav_calc) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new CalcFragment()).commit();
            getSupportActionBar().setTitle("BMI Calculator");
        } else if (id == R.id.nav_setting) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new SettingFragment()).commit();
            getSupportActionBar().setTitle("Settings");
        } else if (id == R.id.nav_about) {
            mManager.beginTransaction().replace(R.id.cont_Frame, new AboutFragment()).commit();
            getSupportActionBar().setTitle("About");
        } else if (id == R.id.nav_logout) {
            promptLogout();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDataUser() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String username = getIntent().getStringExtra("username");
        cursor = db.rawQuery("select username,email from user where username = '"+username+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            txt_Username.setText(cursor.getString(0));
            txt_Email.setText(cursor.getString(1));
        }
        db.close();
    }

    public void promptLogout() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure want to Sign Out?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void logout() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("Update user set flag = '0'");
        db.close();
    }
}
