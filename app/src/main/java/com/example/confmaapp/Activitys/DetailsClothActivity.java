package com.example.confmaapp.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.confmaapp.R;

public class DetailsClothActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView ref,size,fashion;
    private View color;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_cloth);
        setTitle(getString(R.string.details_cloth));


        photo = findViewById(R.id.imgClothDetail);
        ref = findViewById(R.id.lblRefDetail);
        size = findViewById(R.id.lblSizeDetail);
        fashion = findViewById(R.id.lblFashionDetail);
        color = findViewById(R.id.vColorDetail);

        intent = getIntent();
        bundle = intent.getBundleExtra("data");

        ref.setText(bundle.getString("ref"));
        size.setText(getResources().getString(bundle.getInt("size")));
        fashion.setText(getResources().getString(bundle.getInt("fashion")));
        color.setBackgroundColor(bundle.getInt("color"));
        Bitmap p = StringToBitMap(bundle.getString("photo"));
        photo.setImageBitmap(p);

    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetailsClothActivity.this, ListClothActivity.class);
        startActivity(intent);
    }
}