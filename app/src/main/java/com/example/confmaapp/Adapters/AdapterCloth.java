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

import com.example.confmaapp.Objects.Cloth;
import com.example.confmaapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCloth extends RecyclerView.Adapter<AdapterCloth.ClothViewHolder>{

    private final ArrayList<Cloth> cloths;
    private final onClothClickListener clickListener;

    public AdapterCloth(ArrayList<Cloth> cloths, onClothClickListener clickListener){
        this.cloths = cloths;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ClothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()
        ).inflate(R.layout.item_cloth,parent,false);
        return new ClothViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothViewHolder holder, int position) {
        final Cloth cloth = cloths.get(position);

        //Log.i("img" , cloth.getPhoto_cloth());
        /*cloth.getPhoto_cloth()*/
        Bitmap photo = StringToBitMap(cloth.getPhoto_cloth());
        holder.photoCloth.setImageBitmap(photo);
        holder.ref.setText(cloth.getRef());
        holder.size.setText(cloth.getSize().getName());
        holder.fashion.setText(cloth.getStyle_fashion());
        holder.color.setBackgroundColor(cloth.getColor());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClothClick(cloth);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }

    public static class ClothViewHolder extends RecyclerView.ViewHolder{
        private final CircleImageView photoCloth;
        private final TextView ref;
        private final TextView size;
        private final TextView fashion;
        private final View color;
        private final View v;

        public ClothViewHolder(View itemView){
            super(itemView);
            v = itemView;
            photoCloth = v.findViewById(R.id.imgCloth);
            ref = v.findViewById(R.id.lblRefCloth);
            size = v.findViewById(R.id.lblSizeCloth);
            fashion = v.findViewById(R.id.lblFashionCloth);
            color = v.findViewById(R.id.vColor);
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

    public interface onClothClickListener{
        void onClothClick(Cloth e);
    }
}
