package com.project.doductrung.android_ver1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.project.doductrung.android_ver1.MainActivity.data;

public class Addapter extends BaseAdapter {
    Activity cont;
    ArrayList<User> mon;

    public Addapter(Activity cont, ArrayList<User> mon) {
        this.cont = cont;
        this.mon = mon;
    }

    @Override
    public int getCount() {
        return mon.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) cont.getSystemService(cont.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_nhan_vien,null);
        TextView txt_Nhanvien = (TextView) row.findViewById(R.id.textView_NhanVien);
        final User nhanvien1 = mon.get(position);
        txt_Nhanvien.setText(nhanvien1.username);
        Button btn_xoa = (Button) row.findViewById(R.id.button_xoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cont);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(nhanvien1.username);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return row;
    }
    private void delete(String username) {
//        SQLiteDatabase database = Database.initDatabase(cont,"Login.sqlite");
        data.delete("USER", "USERNAME = ?", new String[]{username});
        mon.clear();
        Cursor cursor = data.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()){
            String username1 = cursor.getString(0);
            String password1 = cursor.getString(1);
            mon.add(new User(username1,password1));
        }
        notifyDataSetChanged();
    }
}
