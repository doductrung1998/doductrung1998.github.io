package com.project.doductrung.android_ver1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DATABASE_NAME = "Login.sqlite";
    public static SQLiteDatabase data;
    public static ArrayList<User> user;
    EditText edt_username, edt_password;
    Button btn_login,btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conTroll();
        readData();
    }

    private void conTroll() {
        user = new ArrayList<>();
        edt_username = (EditText) findViewById(R.id.editText_Username);
        edt_password = (EditText) findViewById(R.id.editText_Password);
        btn_login = (Button) findViewById(R.id.button_Login);
        btn_register = (Button) findViewById(R.id.button_Register);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void readData() {
        data = Database.initDatabase(this , DATABASE_NAME);
        Cursor checkData = data.rawQuery("SELECT * FROM USER",null);
        user.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            String username = checkData.getString(0);
            String password = checkData.getString(1);
            user.add(new User(username,password));
        }
    }

    public boolean checkUser(){
        int check =0;
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();
        for (int i = 0 ; i < user.size() ; i++){
            if (username.equals("ADMIN") && password.equals("ADMIN")){
                check = -1;
            } else if((username.equals(user.get(i).username)) && (password.equals(user.get(i).userpassword))){
            check = 0;
            } else{ check = 1;}
        }
        //Xu ly account
        if (check == -1){//(ADMIN)
            AlertDialog.Builder thongbao0 = new AlertDialog.Builder(MainActivity.this);
            thongbao0.setTitle("THÔNG BÁO");
            thongbao0.setMessage("ĐĂNG NHẬP THÀNH CÔNG");
            thongbao0.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent admin = new Intent(MainActivity.this,QuanLy_NhanVien_Mon.class);
                    startActivity(admin);
                }
            });
            thongbao0.show();
            return true;
        } else if(check==0) {
            AlertDialog.Builder thongbao1 = new AlertDialog.Builder(MainActivity.this);
            thongbao1.setTitle("THÔNG BÁO");
            thongbao1.setMessage("ĐĂNG NHẬP THÀNH CÔNG");
            thongbao1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent table = new Intent(MainActivity.this,TableActivity.class);
                    startActivity(table);
                }
            });
            thongbao1.show();
            return true;
        }else if (check == 1){
            AlertDialog.Builder thongbao2 = new AlertDialog.Builder(MainActivity.this);
            thongbao2.setTitle("THÔNG BÁO");
            thongbao2.setMessage("ĐĂNG NHẬP THẤT BẠI");
            thongbao2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            thongbao2.show();
        }
        return false;
    }
}
