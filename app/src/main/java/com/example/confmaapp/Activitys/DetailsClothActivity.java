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
import com.example.confmaapp.Objects.Size;
import com.example.confmaapp.R;

public class DetailsClothActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView ref,size,fashion;
    private View color;
    private Intent intent;
    private Bundle bundle;
    private Cloth cloth;
    private Size size_;

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
        size_ = (Size) intent.getSerializableExtra("size");

        ref.setText(bundle.getString("ref"));
        size.setText(size_.getName());
        fashion.setText(getResources().getString(bundle.getInt("fashion")));
        color.setBackgroundColor(bundle.getInt("color"));
        Bitmap p = StringToBitMap(bundle.getString("photo"));
        photo.setImageBitmap(p);

        cloth = new Cloth(
                bundle.getString("photo"),
                bundle.getString("ref"),
                size_,
                bundle.getInt("color"),
                bundle.getInt("fashion")
        );

    }

    public void delete( View view){
        String True , False;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_cloth);
        builder.setMessage(R.string.question_delete_cloth);
        True = getString(R.string.option_yes);
        False = getString(R.string.option_not);

        final Context context = view.getContext();

        builder.setPositiveButton(True, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cloth.delete();
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

        AlertDialog alertDialog =builder.create();
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
        Intent intent = new Intent(DetailsClothActivity.this, ListClothActivity.class);
        startActivity(intent);
    }
}