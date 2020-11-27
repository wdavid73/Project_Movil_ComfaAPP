package com.example.confmaapp.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.Objects.Rental;
import com.example.confmaapp.R;

public class DetailsRentalActivity extends AppCompatActivity {

    private ImageView photoCloth;
    private TextView date_now , date_return , price , refCloth , sizeCloth , fashionCloth;
    private View colorCloth;
    private Intent intent;
    private Bundle bundle;
    private Cloth cloth;
    private Rental rental;

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
        sizeCloth.setText(cloth.getSize().getName());
        fashionCloth.setText(cloth.getStyle_fashion());
        colorCloth.setBackgroundColor(cloth.getColor());
        Bitmap p = StringToBitMap(cloth.getPhoto_cloth());
        photoCloth.setImageBitmap(p);

        rental = new Rental(
                bundle.getString("date_now"),
                bundle.getString("date_return"),
                bundle.getString("price"),
                cloth
        );
    }

    public void delete(View view){
        String True , False;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_rental);
        builder.setMessage(R.string.question_delete_rental);
        True = getString(R.string.option_yes);
        False = getString(R.string.option_not);
        final Context context = view.getContext();

        builder.setPositiveButton(True, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rental.delete();
                onBackPressed();
            }
        });

        builder.setNegativeButton(False, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(
                        context,
                        getString(R.string.operation_denied),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
        Intent intent = new Intent(DetailsRentalActivity.this, ListRentalsActivity.class);
        startActivity(intent);
    }
}