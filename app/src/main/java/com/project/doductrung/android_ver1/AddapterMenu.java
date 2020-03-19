package com.project.doductrung.android_ver1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddapterMenu extends BaseAdapter {
    Activity cont;
    ArrayList<Menu> menus;

    public AddapterMenu(Activity cont, ArrayList<Menu> menus) {
        this.cont = cont;
        this.menus = menus;
    }

    @Override

    public int getCount() {
        return menus.size();
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
        View row = inflater.inflate(R.layout.activity_menu_properties,null);
        //Khai bao
        Button btn_Xoa = (Button) row.findViewById(R.id.btn_Menu_Xoa);
        Button btn_Sua = (Button) row.findViewById(R.id.btn_Menu_Sua);
        TextView txt_idMon = (TextView) row.findViewById(R.id.txt_Menu_Id);
        TextView txt_tenMon = (TextView) row.findViewById(R.id.txt_Menu_Ten);
        TextView txt_price = (TextView) row.findViewById(R.id.txt_Menu_Price);
        ImageView img_pictue = (ImageView) row.findViewById(R.id.img_Menu);
        final Menu menu1 = menus.get(position);
        txt_idMon.setText(menu1.getId() + "");
        txt_tenMon.setText(menu1.getTen());
        txt_price.setText(menu1.getGiaTien() + "");
        Bitmap bmPictue = BitmapFactory.decodeByteArray(menu1.picture, 0, menu1.picture.length);
        img_pictue.setImageBitmap(bmPictue);

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(cont);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa món này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(menu1.getId());
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
        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sua = new Intent(cont,UpdateMenuActivity.class);
                sua.putExtra("IDMON",menu1.getId());
                cont.startActivity(sua);
            }
        });

        return row;
    }
    private void delete(int idMon) {
        SQLiteDatabase database = Database.initDatabase(cont,"QuanLyCaPhe.sqlite");
        database.delete("MENU", "IDMON = ?", new String[]{idMon + ""});
        menus.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM MENU", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            int gia = cursor.getInt(2);
            byte[] anh = cursor.getBlob(3);

            menus.add(new Menu(id, ten, gia, anh));
        }
        notifyDataSetChanged();
    }
}
