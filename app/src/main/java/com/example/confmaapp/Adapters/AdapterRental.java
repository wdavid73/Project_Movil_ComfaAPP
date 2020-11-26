package com.example.confmaapp.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confmaapp.Objects.Rental;
import com.example.confmaapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRental extends RecyclerView.Adapter<AdapterRental.RentalViewHolder>{

    private ArrayList<Rental> rentals;
    private onRentalClickListener clickListener;

    public AdapterRental(ArrayList<Rental> rentals, onRentalClickListener clickListener) {
        this.rentals = rentals;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()
        ).inflate(R.layout.item_rental,parent,false);
        return new RentalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        final Rental rental = rentals.get(position);

        Bitmap photo = StringToBitMap(rental.getCloth().getPhoto_cloth());
        holder.photoClothRental.setImageBitmap(photo);
        holder.date_now.setText(rental.getDate_now());
        holder.date_return.setText(rental.getDate_return());
        holder.price.setText(rental.getPrice());
        holder.refCloth.setText(rental.getCloth().getRef());
        holder.sizeCloth.setText(rental.getCloth().getSize());
        holder.fashionCloth.setText(rental.getCloth().getStyle_fashion());
        holder.colorCloth.setBackgroundColor(rental.getCloth().getColor());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onRentalClick(rental);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rentals.size();
    }


    public static class RentalViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView photoClothRental;
        private TextView date_now , date_return , price , refCloth , sizeCloth , fashionCloth;
        private View colorCloth , v;


        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            photoClothRental = v.findViewById(R.id.imgRentalCloth);
            date_now = v.findViewById(R.id.lblDateNowRental);
            date_return = v.findViewById(R.id.lblDateReturnRental);
            price = v.findViewById(R.id.lblPriceRental);
            refCloth = v.findViewById(R.id.lblRefClothRental);
            sizeCloth = v.findViewById(R.id.lblSizeClothRental);
            fashionCloth = v.findViewById(R.id.lblFashionClothRental);
            colorCloth = v.findViewById(R.id.vColorClothRental);

        }
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

    public interface onRentalClickListener{
        void onRentalClick(Rental r);
    }
}
