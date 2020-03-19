package com.project.doductrung.android_ver1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    final String DATABASE_NAME = "QuanLyCaPhe.sqlite";
    final int REQUEST_ADD = 123;
    SQLiteDatabase database;
    //Khai bao
    ArrayList<Menu> menus;
    ListView listMenu;
    AddapterMenu addapter;
    //VIEW
    Button menu_back , menu_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        addControls();
        readData();
    }
    private void readData() {
        database = Database.initDatabase(this , DATABASE_NAME);
        Cursor checkData = database.rawQuery("SELECT * FROM MENU",null);
        menus.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            int idMon = checkData.getInt(0);
            String tenMon = checkData.getString(1);
            int giaTien = checkData.getInt(2);
            byte[] picture = checkData.getBlob(3);
            menus.add(new Menu(idMon,tenMon,giaTien,picture));
        }
        addapter.notifyDataSetChanged();
    }
    private void addControls() {
        menu_add = (Button) findViewById(R.id.btn_menu_add);
        menu_back = (Button) findViewById(R.id.btn_menu_back);
        listMenu = (ListView) findViewById(R.id.listView_Menu);
        menus = new ArrayList<>();
        addapter = new AddapterMenu(this,menus);
        listMenu.setAdapter(addapter);
        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MenuActivity.this,QuanLy_NhanVien_Mon.class);
                startActivity(back);
            }
        });
        menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MenuActivity.this,AddMenuActivity.class);
                startActivity(add);
            }
        });
    }

}
