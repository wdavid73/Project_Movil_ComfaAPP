package com.example.confmaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.confmaapp.Activitys.ListClothActivity;
import com.example.confmaapp.Activitys.ListRentalsActivity;
import com.example.confmaapp.Activitys.RegisterClothActivity;
import com.example.confmaapp.Activitys.RegisterRentalActivity;
import com.example.confmaapp.Activitys.RegisterSizesAcitivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(getString(R.string.home));
    }

    public void GoToRegisterCloth(View v){
        Intent register_cloth = new Intent(HomeActivity.this , RegisterClothActivity.class);
        startActivity(register_cloth);
        
    }

    public void GoToListCloth(View v){
        Intent list_cloth = new Intent(HomeActivity.this , ListClothActivity.class);
        startActivity(list_cloth);

    }

    public void GoToRegisterRental(View v){
        Intent register_rental = new Intent(HomeActivity.this , RegisterRentalActivity.class);
        startActivity(register_rental);
    }

    public void GoToListRental(View v){
        Intent list_rental = new Intent(HomeActivity.this , ListRentalsActivity.class);
        startActivity(list_rental);
    }

    public void GoToRegisterSize(View v){
        Intent register_size = new Intent(HomeActivity.this , RegisterSizesAcitivity.class);
        startActivity(register_size);
    }
}