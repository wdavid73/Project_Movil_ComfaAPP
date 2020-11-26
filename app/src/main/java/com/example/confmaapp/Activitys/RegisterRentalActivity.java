package com.example.confmaapp.Activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confmaapp.Adapters.AdapterClothRental;
import com.example.confmaapp.Fragments.DatePickerFragment;
import com.example.confmaapp.HomeActivity;
import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.Objects.Data;
import com.example.confmaapp.Objects.Rental;
import com.example.confmaapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterRentalActivity
        extends AppCompatActivity
        implements View.OnClickListener , AdapterClothRental.onClothRentalListener {

    private EditText date , price;
    private AdapterClothRental adapterClothRental;
    private ArrayList<Cloth> cloths;
    private Cloth cloth;
    private String date_now, selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rental);
        setTitle(getString(R.string.title_register_rental));

        date = findViewById(R.id.txtdateReturn);
        price = findViewById(R.id.txtPriceRental);
        TextView required_cloth = findViewById(R.id.lblClothRequired);

        date.setOnClickListener(this);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        date_now = day + "/" + (month+1) + "/" + year;

        RecyclerView list_cloths = findViewById(R.id.lstClothRentals);
        cloths = Data.get_cloths();
        if (cloths.size() > 0){
            adapterClothRental = new AdapterClothRental(cloths,this);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(RecyclerView.VERTICAL);
            list_cloths.setLayoutManager(llm);
            list_cloths.setAdapter(adapterClothRental);
            required_cloth.setText("");
        }else{
            required_cloth.setText(R.string.cloth_required);
            required_cloth.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        //Log.i("rentals" , String.valueOf(Data.get_rentals()));

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtdateReturn) {
            showDatePickerDialog();
        }
    }

    @Override
    public void onClothRentalClick(int adapterPosition) {
        cloth = cloths.get(adapterPosition);
        //Log.d("cloth", String.valueOf(cloths.get(adapterPosition)));
    }

    public void showDatePickerDialog(){
        DatePickerFragment newFragment = DatePickerFragment
                .newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker,
                                  int year, int month, int day) {
                selectedDate = day + "/" + (month+1) + "/" + year;
                date.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void  onBackPressed(){
        finish();
        Intent intent = new Intent(RegisterRentalActivity.this , HomeActivity.class);
        startActivity(intent);
    }

    public void save(View v){
        String dn, p, dr;
        Cloth c;
        Rental rental;
        if(validate()){
            rental = new Rental(date_now,selectedDate,price.getText().toString(),cloth);
            rental.save();
            Snackbar.make(v, R.string.save_rental_successful, Snackbar.LENGTH_LONG).show();
            clear();
        }
    }

    public void clear(){
        date.setText("");
        price.setText("");
    }

    public boolean validate(){

        if(price.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.please_input_price , Toast.LENGTH_LONG).show();
            price.requestFocus();
            return false;
        }

        if(date.getText().toString().isEmpty()){
            Toast.makeText(this , R.string.please_input_date, Toast.LENGTH_LONG).show();
            return false;
        }

        if(cloths.size() <= 0){
            Toast.makeText(this , R.string.cloth_required , Toast.LENGTH_LONG).show();
            return false;
        }

        if(cloth == null){
            Toast.makeText(this , R.string.please_choose_cloth , Toast.LENGTH_LONG).show();
            return false;
        }

        if(date_now.isEmpty()){
            Toast.makeText(this , R.string.error_date_now , Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}