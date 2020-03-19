package com.project.doductrung.android_ver1;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.project.doductrung.android_ver1.MainActivity.user;

public class RegisterActivity extends AppCompatActivity {
    EditText edt_Username, edt_Password, edt_Confirm;
    Button btn_Accept , btn_Cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControl();
        addEvent();
//        System.out.println(user.get(0).username);
    }

    private void addEvent() {
        btn_Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(cancel);
            }
        });
    }

    private void insertUser() {
        String username = edt_Username.getText().toString();
        String password = edt_Password.getText().toString();
        String confirm = edt_Confirm.getText().toString();
        int check = 0;
        for (int i = 0; i < user.size() ; i++){
            check = 0;
            if(username.equals("") || password.equals("") || confirm.equals("")){
                check = 1;
                AlertDialog.Builder thongbao1 = new AlertDialog.Builder(RegisterActivity.this);
                thongbao1.setTitle("Lỗi 01");
                thongbao1.setMessage("Không được để trống!");
                thongbao1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                thongbao1.show();
            }else if(username.equals(user.get(i).username)){
                check = 1;
                AlertDialog.Builder thongbao2 = new AlertDialog.Builder(RegisterActivity.this);
                thongbao2.setTitle("Lỗi 02");
                thongbao2.setMessage("Tài khoản đã tồn tại");
                thongbao2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                thongbao2.show();
                break;
            }else if (!password.equals(confirm)){
                check = 1;
                AlertDialog.Builder thongbao3 = new AlertDialog.Builder(RegisterActivity.this);
                thongbao3.setTitle("Lỗi 03");
                thongbao3.setMessage("Password not equals Confirm");
                thongbao3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                thongbao3.show();
                break;
            }
        }
        if (check == 0){
            ContentValues them = new ContentValues();
            them.put("USERNAME",username);
            them.put("USERPASSWORD",password);

            SQLiteDatabase data = Database.initDatabase(RegisterActivity.this,"Login.sqlite");
            data.insert("USER",null,them);
            Intent acept = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(acept);
        }

    }
    private void addControl() {
        edt_Username = findViewById(R.id.editText_Register_Username);
        edt_Password = findViewById(R.id.editText_Register_Password);
        edt_Confirm = findViewById(R.id.editText_Register_Confirm);
        btn_Accept = findViewById(R.id.button_Accept);
        btn_Cancel = findViewById(R.id.button_Cancel);
    }

}
