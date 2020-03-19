package com.project.doductrung.android_ver1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.project.doductrung.android_ver1.AddapterTableMenu.tong;

public class TableMenuActivity extends AppCompatActivity {
    public static ArrayList<Integer> soluong = new ArrayList<>();
    final String DATABASE_NAME = "Menu1.sqlite";
    final int REQUEST_ADD = 123;
    SQLiteDatabase database;
    //Khai bao
    ArrayList<Menu1> menus;
    ListView listTableMenu;
    AddapterTableMenu addapter;
    //VIEW
    Button menu_table_back , menu_table_on;
    int id = -999;
    TextView txtMoney;
    public static int id1,status1,money1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_menu);
        addControls();
        addEvent();
        readData();
        initUI();
        addSoluong();
    }

    private void addEvent() {
        menu_table_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ID",id1);
                System.out.println(status1);
                if(status1==1){
                    status1 = 0;
                    money1 = 0;
                    tong = 0;
                }else if (status1 ==0){
                    status1 = 1;
                }
                money1 = money1 + tong;
                contentValues.put("STATUS",status1);
                contentValues.put("MONEY",money1);
                tong = 0;
                System.out.println(contentValues);
//                Toast.makeText(TableMenuActivity.this,status1,Toast.LENGTH_LONG).show();
                SQLiteDatabase database1 = Database.initDatabase(TableMenuActivity.this, "Table.sqlite");
                database1.update("TABLES", contentValues, "ID = ?", new String[] {id1 + ""});
                Intent intent = new Intent(TableMenuActivity.this, TableActivity.class);
                startActivity(intent);
            }
        });
    }
//    private void update(){
//        String id1 = edt_update_id.getText().toString();
//        String ten = edt_update_ten.getText().toString();
//        String price = edt_update_price.getText().toString();
//        byte[] anh = getByteArrayFromImageView(img_update);
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("IDMON", id1);
//        contentValues.put("TENMON", ten);
//        contentValues.put("GIATIEN", price);
//        contentValues.put("PICTURE", anh);
//
//        SQLiteDatabase database = Database.initDatabase(this, "QuanLyCaPhe.sqlite");
//        database.update("MENU", contentValues, "IDMON = ?", new String[] {id + ""});
//        Intent intent = new Intent(this, MenuActivity.class);
//        startActivity(intent);
//
//    }

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
            int soluong = checkData.getInt(4);
            menus.add(new Menu1(idMon,tenMon,giaTien,picture,soluong));
        }
        addapter.notifyDataSetChanged();
    }
    private void addControls() {
        txtMoney = (TextView) findViewById(R.id.txt_Table_Money);
        menu_table_on = (Button) findViewById(R.id.btn_Status);
//        menu_add = (Button) findViewById(R.id.btn_menu_add);
        menu_table_back = (Button) findViewById(R.id.btn_table_menu_back);
        listTableMenu = (ListView) findViewById(R.id.listView_Table_Menu);
        menus = new ArrayList<>();
        addapter = new AddapterTableMenu(this,menus);
        listTableMenu.setAdapter(addapter);
        menu_table_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status1 == 1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("ID", id1);
//                System.out.println(status1);
//                if(status1==1){
//                    status1 = 0;
//                    tong = 0;
//                }else if (status1 ==0){
//                    status1 = 1;
//                }
                    money1 = money1 + tong;
                    contentValues.put("STATUS", status1);
                    contentValues.put("MONEY", money1);
                    tong = 0;
                    System.out.println(contentValues);
                    SQLiteDatabase database1 = Database.initDatabase(TableMenuActivity.this, "Table.sqlite");
                    database1.update("TABLES", contentValues, "ID = ?", new String[]{id1 + ""});
                }
                Intent back = new Intent(TableMenuActivity.this,TableActivity.class);
                startActivity(back);
            }
        });

    }
    private void initUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -999);
        SQLiteDatabase database = Database.initDatabase(this,"Table.sqlite");
        Cursor cursor = database.rawQuery("SELECT * FROM TABLES WHERE ID = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        id1 = cursor.getInt(0);
        status1 = cursor.getInt(1);
        money1 = cursor.getInt(2);
        txtMoney.setText(money1 + "");
        if(status1 == 1){
            menu_table_on.setText("OFF");
        }else if (status1 == 0){
            menu_table_on.setText("ON");
        }
    }
    public void addSoluong(){
        for(int i = 0 ; i < menus.size() ; i++){
            soluong.add(0);
        }
    }


}
