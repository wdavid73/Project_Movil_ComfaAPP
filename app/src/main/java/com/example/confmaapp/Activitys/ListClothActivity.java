package com.example.confmaapp.Activitys;

import android.content.Intent;
import android.os.Bundle;

import com.example.confmaapp.Adapters.AdapterCloth;
import com.example.confmaapp.HomeActivity;
import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.Objects.Data;
import com.example.confmaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class ListClothActivity extends AppCompatActivity implements AdapterCloth.onClothClickListener {

    private RecyclerView list_cloths;
    private AdapterCloth adapterCloth;
    private LinearLayoutManager llm;
    private ArrayList<Cloth> cloths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cloth);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.list_cloth));

        list_cloths = findViewById(R.id.lstCloths);
        cloths = Data.get_cloths();

        adapterCloth = new AdapterCloth(cloths,this);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);

        list_cloths.setLayoutManager(llm);
        list_cloths.setAdapter(adapterCloth);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(ListClothActivity.this , RegisterClothActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClothClick(Cloth c) {
        Intent intent;
        Bundle bundle;
        bundle = new Bundle();
        bundle.putString("photo" , c.getPhoto_cloth());
        bundle.putString("ref",c.getRef());
        bundle.putInt("size",c.getSize());
        bundle.putInt("fashion",c.getStyle_fashion());
        bundle.putInt("color" , c.getColor());

        intent = new Intent(ListClothActivity.this , DetailsClothActivity.class);
        intent.putExtra("data" , bundle);
        startActivity(intent);
        finish();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ListClothActivity.this , HomeActivity.class);
        startActivity(intent);
    }
}