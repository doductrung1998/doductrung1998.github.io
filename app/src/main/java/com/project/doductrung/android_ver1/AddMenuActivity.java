package com.project.doductrung.android_ver1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddMenuActivity extends AppCompatActivity {
    final String DATABASE_NAME = "QuanLyCaPhe.sqlite";
    final int REQUEST_TAKE_PHOTO = 111;
    final int REQUEST_CHOOSE_PHOTO = 222;
    int id = -999;
    int id2 ;
    EditText edt_add_id , edt_add_ten , edt_add_price;
    ImageView img_add;
    Button btn_add_chon , btn_add_chup , btn_add_ok, btn_add_huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        addControls();
        addEvents();
    }
    private void addControls() {
        btn_add_chon = (Button) findViewById(R.id.btn_Add_ChonHinh);
        btn_add_chup = (Button) findViewById(R.id.btn_Add_ChupHinh);
        btn_add_ok = (Button) findViewById(R.id.btn_add_ok);
        btn_add_huy = (Button) findViewById(R.id.btn_add_huy);

        edt_add_id = (EditText) findViewById(R.id.edt_Add_Id);
        edt_add_ten = (EditText) findViewById(R.id.edt_Add_Ten);
        edt_add_price = (EditText) findViewById(R.id.edt_Add_GiaTien);
        img_add = (ImageView) findViewById(R.id.img_Add_Menu);
    }
    private void addEvents(){
        btn_add_chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btn_add_chup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btn_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        btn_add_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(AddMenuActivity.this,MenuActivity.class);
                startActivity(cancel);
            }
        });
    }
    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    img_add.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img_add.setImageBitmap(bitmap);
            }
        }
    }
    private void add(){
        String id1 = edt_add_id.getText().toString();
        String ten = edt_add_ten.getText().toString();
        String price = edt_add_price.getText().toString();
        byte[] anh = getByteArrayFromImageView(img_add);

        ContentValues contentValues = new ContentValues();
        contentValues.put("IDMON", id1);
        contentValues.put("TENMON", ten);
        contentValues.put("GIATIEN", price);
        contentValues.put("PICTURE", anh);

        SQLiteDatabase database = Database.initDatabase(this, "QuanLyCaPhe.sqlite");
        database.insert("MENU",null,contentValues);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }
    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
