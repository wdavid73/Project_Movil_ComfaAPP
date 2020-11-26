package com.example.confmaapp.Adapters;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.KeyEvent;
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

public class AdapterClothRental extends RecyclerView.Adapter<AdapterClothRental.ClothRentalViewHolder>{

    private final ArrayList<Cloth> cloths;
    private final onClothRentalListener clothRentalClickListener;
    private int focusedItem = -1;
    boolean isSelected;

    public AdapterClothRental(ArrayList<Cloth> cloths , onClothRentalListener clickListener){
        this.cloths = cloths;
        this.clothRentalClickListener = clickListener;
    }


    @NonNull
    @Override
    public ClothRentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(
                parent.getContext()
        ).inflate(R.layout.item_cloth,parent,false);
        return new ClothRentalViewHolder(v, clothRentalClickListener);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ClothRentalViewHolder holder, final int position) {
        final Cloth cloth = cloths.get(position);

        holder.itemView.setBackgroundColor(
                focusedItem == position ?
                        R.color.colorPrimaryDark : Color.TRANSPARENT
        );

        Bitmap photo = StringToBitMap(cloth.getPhoto_cloth());
        holder.photoCloth.setImageBitmap(photo);
        holder.ref.setText(cloth.getRef());
        holder.size.setText(cloth.getSize());
        holder.fashion.setText(cloth.getStyle_fashion());
        holder.color.setBackgroundColor(cloth.getColor());
    }

    @Override
    public int getItemCount() {
        return cloths.size();
    }

    public class ClothRentalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final CircleImageView photoCloth;
        private final TextView ref;
        private final TextView size;
        private final TextView fashion;
        private final View color;
        private final View v;
        onClothRentalListener clickListener;

        public ClothRentalViewHolder(View itemView , onClothRentalListener clickListener){
            super(itemView);
            v = itemView;
            photoCloth = v.findViewById(R.id.imgCloth);
            ref = v.findViewById(R.id.lblRefCloth);
            size = v.findViewById(R.id.lblSizeCloth);
            fashion = v.findViewById(R.id.lblFashionCloth);
            color = v.findViewById(R.id.vColor);
            this.clickListener = clickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            notifyItemChanged(focusedItem);
            focusedItem = getLayoutPosition();
            notifyItemChanged(focusedItem);
            clickListener.onClothRentalClick(getAdapterPosition());
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();

                // Return false if scrolled to the bounds and allow focus to move off the list
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_DPAD_DOWN) {
                        return tryMoveSelection(lm, 1);
                    } else if (i == KeyEvent.KEYCODE_DPAD_UP) {
                        return tryMoveSelection(lm, -1);
                    }
                }

                return false;
            }
        });
    }

    private boolean tryMoveSelection(RecyclerView.LayoutManager lm, int direction) {

        int tryFocusItem = focusedItem + direction;

        // If still within valid bounds, move the selection, notify to redraw, and scroll
        if (tryFocusItem >= 0 && tryFocusItem < getItemCount()) {
            notifyItemChanged(focusedItem);
            focusedItem = tryFocusItem;
            notifyItemChanged(focusedItem);
            lm.scrollToPosition(focusedItem);
            return true;
        }

        return false;
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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public interface onClothRentalListener {
        void onClothRentalClick(int adapterPosition);
    }

}
