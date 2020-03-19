package com.project.doductrung.android_ver1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
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

import static com.project.doductrung.android_ver1.TableActivity.tables;
import static com.project.doductrung.android_ver1.TableMenuActivity.money1;
import static com.project.doductrung.android_ver1.TableMenuActivity.soluong;

public class AddapterTableMenu extends BaseAdapter {
    Activity cont;
    ArrayList<Menu1> menus;
    public static int tong = 0;
    int k = 0;
    public AddapterTableMenu(Activity cont, ArrayList<Menu1> menus) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) cont.getSystemService(cont.LAYOUT_INFLATER_SERVICE);
        final View row = inflater.inflate(R.layout.activity_table_menu_properties,null);
        //Khai bao
        Button btn_Xoa = (Button) row.findViewById(R.id.btn_Table_Menu_Xoa);
        Button btn_Them = (Button) row.findViewById(R.id.btn_Table_Menu_Them);
        final TextView txt_idMon = (TextView) row.findViewById(R.id.txt_Table_Menu_Id);
        TextView txt_tenMon = (TextView) row.findViewById(R.id.txt_Table_Menu_Ten);
        TextView txt_price = (TextView) row.findViewById(R.id.txt_Table_Menu_Price);
        TextView txt_soluong = (TextView) row.findViewById(R.id.txt_soluong);
        ImageView img_pictue = (ImageView) row.findViewById(R.id.img__Table_Menu);
        final Menu1 menu1 = menus.get(position);
        txt_idMon.setText(menu1.getId() + "");
        txt_tenMon.setText(menu1.getTen());
        txt_price.setText(menu1.getGiaTien() + "");
        txt_soluong.setText(menu1.getSoluong() + "");
        Bitmap bmPictue = BitmapFactory.decodeByteArray(menu1.picture, 0, menu1.picture.length);
        img_pictue.setImageBitmap(bmPictue);

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu1.setSoluong(menu1.getSoluong()-1);
//                AlertDialog.Builder builder = new AlertDialog.Builder(cont);
//                builder.setIcon(android.R.drawable.ic_delete);
//                builder.setTitle("Xác nhận xóa");
//                builder.setMessage("Bạn có chắc chắn muốn xóa món này?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        delete(menu1.getId());
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
                TextView txt = (TextView)cont.findViewById(R.id.txt_Table_Money);
                money1 -= menu1.getGiaTien();
                txt.setText(money1 + "");
                notifyDataSetChanged();
            }
        });
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                menu1.setSoluong(menu1.getSoluong() + 1);
//                ContentValues contentValues = new ContentValues();
//                contentValues.put("IDMON",menu1.getId());
//                contentValues.put("TENMON",menu1.getTen());
//                contentValues.put("GIATIEN",menu1.getGiaTien());
//                contentValues.put("PICTURE",menu1.getPicture());
//                contentValues.put("SOLUONG",menu1.getSoluong());
//                SQLiteDatabase database1 = Database.initDatabase(cont, "Menu1.sqlite");
//                database1.update("MENU", contentValues, "IDMON = ?", new String[]{menu1.getId() + ""});
                TextView txt = (TextView) cont.findViewById(R.id.txt_Table_Money);
//                if(hientai == 0){
//                    tong = tong + menu1.getGiaTien();
//                    txt.setText(tong + "");
//                    }else {
                    money1 += menu1.getGiaTien();
                    txt.setText(money1 + "");
//                    tong = tong + menu1.getGiaTien();
//                }

//                Intent sua = new Intent(cont,TableMenuActivity.class);
//                sua.putExtra("TONG",tong);
//                cont.startActivity(sua);
                notifyDataSetChanged();
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
            int soluong = cursor.getInt(4);
            menus.add(new Menu1(id, ten, gia, anh,soluong));
        }
        notifyDataSetChanged();
    }

}
