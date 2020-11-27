package com.example.confmaapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.confmaapp.Objects.Size;
import com.example.confmaapp.R;
import com.google.android.material.snackbar.Snackbar;

public class RegisterSizesAcitivity extends AppCompatActivity {

    private EditText name , type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sizes);
        setTitle(getString(R.string.register_size));

        name = findViewById(R.id.txtNameSize);
        type = findViewById(R.id.txtTypeSize);
    }

    public void saveSize(View v){
        String n,t;
        Size size;
        if (validate()){
            n = name.getText().toString();
            t = type.getText().toString();

            size = new Size(n,t);
            size.save();
            Snackbar.make(v, R.string.register_size_successful, Snackbar.LENGTH_LONG).show();
            clear();
        }
    }

    public void clear(){
        name.setText("");
        type.setText("");
    }

    public boolean validate(){
        if(name.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.please_input_name_size,
                    Toast.LENGTH_LONG).show();
            name.requestFocus();
            return false;
        }

        if(type.getText().toString().isEmpty()){
            Toast.makeText(this,
                    R.string.please_input_type_size,
                    Toast.LENGTH_LONG).show();
            type.requestFocus();
            return false;
        }
        return true;
    }
}