package com.example.confmaapp.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confmaapp.Adapters.AdapterRental;
import com.example.confmaapp.Objects.Data;
import com.example.confmaapp.Objects.Rental;
import com.example.confmaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListRentalsActivity extends AppCompatActivity implements AdapterRental.onRentalClickListener {

    private RecyclerView lst_rentals;
    private AdapterRental adapterRental;
    private LinearLayoutManager llm;
    private ArrayList<Rental> rentals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rentals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.list_rentals));

        lst_rentals = findViewById(R.id.lstRentals);
        rentals = Data.get_rentals();

        adapterRental = new AdapterRental(rentals, this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);

        lst_rentals.setLayoutManager(llm);
        lst_rentals.setAdapter(adapterRental);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRentalsActivity.this , RegisterRentalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onRentalClick(Rental r) {
        Intent intent;
        Bundle bundle;
        bundle = new Bundle();
        bundle.putString("date_now" , r.getDate_now());
        bundle.putString("date_return" , r.getDate_return());
        bundle.putString("price" , r.getPrice());

        intent = new Intent(ListRentalsActivity.this , DetailsRentalActivity.class);
        intent.putExtra("data" , bundle);
        intent.putExtra("rental_cloth" , r.getCloth());
        startActivity(intent);
        finish();
    }

}