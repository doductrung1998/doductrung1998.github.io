package com.project.doductrung.android_ver1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.InetAddresses;
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

public class UpdateMenuActivity extends AppCompatActivity {
    final String DATABASE_NAME = "QuanLyCaPhe.sqlite";
    final int REQUEST_TAKE_PHOTO = 111;
    final int REQUEST_CHOOSE_PHOTO = 222;
    int id = -999;
    int id2 ;
    EditText edt_update_id , edt_update_ten , edt_update_price;
    ImageView img_update;
    Button btn_update_chon , btn_update_chup , btn_update_ok, btn_update_huy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);
        addControls();
        addEvents();
        initUI();

    }
    private void initUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("IDMON", -999);
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM MENU WHERE IDMON = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        int id1 = cursor.getInt(0);
        String ten = cursor.getString(1);
        int price = cursor.getInt(2);
        byte[] anh = cursor.getBlob(3);

        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        img_update.setImageBitmap(bitmap);
        edt_update_price.setText(price + "");
        edt_update_ten.setText(ten);
        edt_update_id.setText(id1 + "");
    }
    private void addControls() {
        btn_update_chon = (Button) findViewById(R.id.btn_UpDate_ChonHinh);
        btn_update_chup = (Button) findViewById(R.id.btn_UpDate_ChupHinh);
        btn_update_ok = (Button) findViewById(R.id.btn_ok);
        btn_update_huy = (Button) findViewById(R.id.btn_huy);

        edt_update_id = (EditText) findViewById(R.id.edt_UpDate_Id);
        edt_update_ten = (EditText) findViewById(R.id.edt_UpDate_Ten);
        edt_update_price = (EditText) findViewById(R.id.edt_UpDate_GiaTien);
        img_update = (ImageView) findViewById(R.id.img_Update_Menu);
    }

    private void addEvents(){
        btn_update_chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btn_update_chup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btn_update_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btn_update_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(UpdateMenuActivity.this,MenuActivity.class);
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
                    img_update.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img_update.setImageBitmap(bitmap);
            }
        }
    }

    private void update(){
        String id1 = edt_update_id.getText().toString();
        String ten = edt_update_ten.getText().toString();
        String price = edt_update_price.getText().toString();
        byte[] anh = getByteArrayFromImageView(img_update);

        ContentValues contentValues = new ContentValues();
        contentValues.put("IDMON", id1);
        contentValues.put("TENMON", ten);
        contentValues.put("GIATIEN", price);
        contentValues.put("PICTURE", anh);

        SQLiteDatabase database = Database.initDatabase(this, "QuanLyCaPhe.sqlite");
        database.update("MENU", contentValues, "IDMON = ?", new String[] {id + ""});
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
