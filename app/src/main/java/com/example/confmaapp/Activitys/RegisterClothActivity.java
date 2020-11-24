package com.example.confmaapp.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.Objects.Data;
import com.example.confmaapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import yuku.ambilwarna.AmbilWarnaDialog;

public class RegisterClothActivity extends AppCompatActivity {

    private ImageView image_select;
    private Button ChooseBtn , btnPickColor;
    private Spinner spSize , spFashion;
    private EditText ref;
    private String[] sizes , fashions;

    private String Document_img1="";

    private static final int IMAGE_PICK_CODE = 1000;    
    private static final int PERMISSION_CODE = 1001;
    int COLOR_SELECTED;
    String img;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cloth);
        setTitle(R.string.title_register_cloth);

        ref = findViewById(R.id.txtRef);
        spFashion = findViewById(R.id.spFashion);
        spSize = findViewById(R.id.spSize);

        sizes = getResources().getStringArray(R.array.sizes);
        fashions = getResources().getStringArray(R.array.fashions);

        ArrayAdapter<String> adapterSizes = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                sizes
        );

        ArrayAdapter<String> adapterFashions = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fashions
        );

        spSize.setAdapter(adapterSizes);
        spFashion.setAdapter(adapterFashions);

        image_select = findViewById(R.id.image_selected);
        ChooseBtn = findViewById(R.id.btnChooseImage);
        btnPickColor = findViewById(R.id.btnPickColor);

        btnPickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        ChooseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        // permission not granted , request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        // permision already granted
                        pickImageFromGallery();
                    }
                }else{
                    // system os is lees then marshmallow
                    pickImageFromGallery();
                }
            }


        });


    }

    private void openColorPicker(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, COLOR_SELECTED, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                //Log.i("onCancel" , "openColorPicker_OnCancel");
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                COLOR_SELECTED = color;
                btnPickColor.setBackgroundColor(COLOR_SELECTED);
            }
        });
        ambilWarnaDialog.show();
    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else{
                    Toast.makeText(this , R.string.permision_denied , Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            // set image to image view
            Uri seletedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(seletedImage,filePath,null,null,null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            thumbnail = getResizedBitmap(thumbnail,400);
            image_select.setImageBitmap(thumbnail);
            img = BitMapToString(thumbnail);

            /*
            Log.i("image_selected" , String.valueOf(data.getData()));
            img = data.getData();
            image_select.setImageURI(data.getData());
            */
        }
    }
    public void save(View v){
        int optSize , optFashion,siz,fash;
        String r;
        Cloth cloth;
        if (validate()){
            optSize = spSize.getSelectedItemPosition();
            optFashion = spFashion.getSelectedItemPosition();
            r = ref.getText().toString();

            switch (optSize){
                case 1:
                    siz = R.string.size_xs;
                case 2:
                    siz = R.string.size_s;
                case 3:
                    siz = R.string.size_m;
                case 4:
                    siz = R.string.size_l;
                case 5:
                    siz = R.string.size_xl;
                    break;
                default:
                    throw new IllegalStateException(getString(R.string.unexpected_value) + optSize);
            }

            switch (optFashion){
                case 1:
                    fash = R.string.general;
                case 2:
                    fash = R.string.to_measure;
                    break;
                default:
                    throw new IllegalStateException(getString(R.string.unexpected_value) + optFashion);
            }

            cloth = new Cloth(img,r,siz,COLOR_SELECTED,fash);
            cloth.save();
            Snackbar.make(v, R.string.cloth_save_success, Snackbar.LENGTH_LONG).show();
            clear();
        }

    }

    public boolean validate(){

        if(img == null){
            Toast.makeText(this,
                    R.string.please_select_a_image,
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }
        if (ref.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.please_input_a_ref,
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }

        if (spSize.getSelectedItemPosition() == 0){
            Toast.makeText(this,
                    R.string.please_select_a_size,
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }

        if(spFashion.getSelectedItemPosition() == 0){
            Toast.makeText(this,
                    R.string.please_select_a_fashion,
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }

        if(COLOR_SELECTED == 0) {
            Toast.makeText(this,
                    R.string.please_pick_a_color,
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }

        return true;
    }

    public void clear(View v){
        clear();
    }

    public void clear(){
        ref.setText("");
        spFashion.setSelection(0);
        spSize.setSelection(0);
        image_select.setImageResource(R.drawable.ic_baseline_image_24);
        btnPickColor.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] b = baos.toByteArray();
        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
        return Document_img1;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
