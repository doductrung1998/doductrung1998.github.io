package com.project.doductrung.android_ver1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    final String DATABASE_NAME = "Table.sqlite";
    SQLiteDatabase database;
    //Khai bao
    public static ArrayList<Table> tables = new ArrayList<>();
    GridView gridViewTable;
    AddapterTable_NhanVien addapterTable_nhanVien;
    //VIEW
    Button btn_table_nhanvien_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        addControlls();
        addEvent();
        readData();
    }

    private void addEvent() {
        btn_table_nhanvien_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent table1 = new Intent(TableActivity.this,MainActivity.class);
                startActivity(table1);
            }
        });
    }

    private void addControlls() {
        gridViewTable = (GridView) findViewById(R.id.girdView_Table);
        btn_table_nhanvien_back = (Button) findViewById(R.id.btn_table_item_back);
        addapterTable_nhanVien = new AddapterTable_NhanVien(TableActivity.this,tables);
        gridViewTable.setAdapter(addapterTable_nhanVien);
        addapterTable_nhanVien.notifyDataSetChanged();

    }
    private void readData() {
        database = Database.initDatabase(this , DATABASE_NAME);
        Cursor checkData = database.rawQuery("SELECT * FROM TABLES",null);
        tables.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            int idTable = checkData.getInt(0);
            int statusTable = checkData.getInt(1);
            int moneyTable = checkData.getInt(2);
            tables.add(new Table(idTable,moneyTable,statusTable));
        }
        addapterTable_nhanVien.notifyDataSetChanged();
    }

}
