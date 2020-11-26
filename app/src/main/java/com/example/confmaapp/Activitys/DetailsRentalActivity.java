package com.example.confmaapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.R;

public class DetailsRentalActivity extends AppCompatActivity {

    private ImageView photoCloth;
    private TextView date_now , date_return , price , refCloth , sizeCloth , fashionCloth;
    private View colorCloth;
    private Intent intent;
    private Bundle bundle, bundle_cloth;
    private Cloth cloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_rental);
        setTitle(getString(R.string.details_rental));

        photoCloth = findViewById(R.id.imgRentalClothDetail);
        date_now = findViewById(R.id.lblDateNowRentalDetail);
        date_return = findViewById(R.id.lblDateReturnRentalDetail);
        price = findViewById(R.id.lblPriceRentalDetail);
        refCloth = findViewById(R.id.lblRefClothRentalDetail);
        sizeCloth = findViewById(R.id.lblSizeClothRentalDetail);
        fashionCloth = findViewById(R.id.lblFashionClothRentalDetail);
        colorCloth = findViewById(R.id.vColorClothRentalDetail);

        intent = getIntent();
        bundle = intent.getBundleExtra("data");

        cloth = (Cloth) intent.getSerializableExtra("rental_cloth");

        date_now.setText(bundle.getString("date_now"));
        date_return.setText(bundle.getString("date_return"));
        price.setText(bundle.getString("price"));

        refCloth.setText(cloth.getRef());
        sizeCloth.setText(cloth.getSize());
        fashionCloth.setText(cloth.getStyle_fashion());
        colorCloth.setBackgroundColor(cloth.getColor());
        Bitmap p = StringToBitMap(cloth.getPhoto_cloth());
        photoCloth.setImageBitmap(p);

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
}