package com.project.doductrung.android_ver1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuanLy_NhanVien_Mon extends AppCompatActivity {
    Button btn_nhanvien,btn_menu , btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly__nhan_vien__mon);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btn_nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nhanvien = new Intent(QuanLy_NhanVien_Mon.this,NhanVienActivity.class);
                startActivity(nhanvien);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(QuanLy_NhanVien_Mon.this,MainActivity.class);
                startActivity(back);
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(QuanLy_NhanVien_Mon.this,MenuActivity.class);
                startActivity(menu);
            }
        });
    }

    private void addControl() {
        btn_nhanvien = (Button) findViewById(R.id.btn_quanly_nhanvien);
        btn_menu = (Button) findViewById(R.id.btn_quanly_menu);
        btn_back = (Button) findViewById(R.id.btn_quanly_back);
    }
}
