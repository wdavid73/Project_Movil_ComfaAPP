package com.example.confmaapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.confmaapp.HomeActivity;
import com.example.confmaapp.R;

public class RegisterRentalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rental);
    }

    /*public void  onBackPressed(){
        finish();
        Intent intent = new Intent(RegisterRentalActivity.this , HomeActivity.class);
        startActivity(intent);
    }*/
}