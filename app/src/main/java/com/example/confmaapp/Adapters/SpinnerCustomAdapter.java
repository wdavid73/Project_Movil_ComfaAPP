package com.example.confmaapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.confmaapp.Activitys.RegisterClothActivity;
import com.example.confmaapp.Objects.Size;


public class SpinnerCustomAdapter extends ArrayAdapter<Size> {

    private Context context;
    private Size[] sizes;


    public SpinnerCustomAdapter(@NonNull Context context, int resource , Size[] sizes) {
        super(context, resource);
        this.context = context;
        this.sizes = sizes;
    }

    @Override
    public int getCount() {
        return sizes.length;
    }

    @Nullable
    @Override
    public Size getItem(int position) {
        return sizes[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position,convertView,parent);
        label.setTextColor(Color.WHITE);
        label.setText(sizes[position].getName() + " - " + sizes[position].getType());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position,convertView,parent);
        label.setTextColor(Color.WHITE);
        label.setText(sizes[position].getName() + " - " + sizes[position].getType());
        return label;
    }
}
