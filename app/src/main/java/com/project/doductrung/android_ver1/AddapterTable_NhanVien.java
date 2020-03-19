package com.project.doductrung.android_ver1;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddapterTable_NhanVien extends BaseAdapter {
    Activity cont;
    ArrayList<Table> list;
    ItemHolder itemHolder;
    public AddapterTable_NhanVien(Activity cont, ArrayList<Table> list) {
        this.cont = cont;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        View row = convertView;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) cont.getSystemService(cont.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_table_item,null);
            itemHolder = new ItemHolder();
            itemHolder.txtTable = (TextView) row.findViewById(R.id.txt_Table_Item);
            itemHolder.imgTable = (ImageView) row.findViewById(R.id.img_Table_Item);
            row.setTag(itemHolder);
        }else{
            itemHolder = (ItemHolder) row.getTag();
        }
        final Table table = list.get(position);
        itemHolder.txtTable.setText("Table" + table.getIdTable());
        if(table.getStatusTable() == 1) {
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
            itemHolder.imgTable.setImageResource(R.drawable.computer_on);
        }
        if (table.getStatusTable() == 0){
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!0");
            itemHolder.imgTable.setImageResource(R.drawable.computer_off);
        }
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oder = new Intent(cont,TableMenuActivity.class);
                oder.putExtra("ID",table.getIdTable());
                cont.startActivity(oder);

            }
        });
        return row;

    }
    public class ItemHolder{
        TextView txtTable;
        ImageView imgTable;
    }
}
