package com.project.doductrung.android_ver1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.project.doductrung.android_ver1.MainActivity.user;
import static com.project.doductrung.android_ver1.R.*;

public class NhanVienActivity extends AppCompatActivity {
    //Database

    final String DATABASE_NAME = "Login.sqlite";
    SQLiteDatabase database;
    //Khai bao
    ArrayList<User> nhanvien;
    ListView listNhanvien;
    Addapter addapter;
    //View
    Button btn_back , button_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_nhanvien);
//        addControl();
        addControlls();
        readData();
    }

    private void readData() {
        database = Database.initDatabase(this , DATABASE_NAME);
        Cursor checkData = database.rawQuery("SELECT * FROM USER",null);
        nhanvien.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            String username = checkData.getString(0);
            String password = checkData.getString(1);
            nhanvien.add(new User(username,password));
        }
        addapter.notifyDataSetChanged();
    }

    private void addControlls() {
        listNhanvien = (ListView) findViewById(R.id.listView);
        btn_back = (Button) findViewById(id.btn_back);
        nhanvien = new ArrayList<>();
//        user.add(new User("aa","vv"));
        addapter = new Addapter(this,nhanvien);
        listNhanvien.setAdapter(addapter);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent back = new Intent(NhanVienActivity.this,QuanLy_NhanVien_Mon.class);
//                startActivity(back);
                onBackPressed();
            }
        });
        button_exit = (Button) findViewById(id.btn_exit);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(NhanVienActivity.this,MainActivity.class);
                startActivity(exit);
            }
        });
    }

}
