package com.example.confmaapp.Objects;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Data {

    public static ArrayList<Cloth> cloths = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();
    public static ArrayList<Size> sizes = new ArrayList<>();

    public static void save_cloth (Cloth c) {
        cloths.add(c);
    }

    public static void save_rental (Rental r){
        rentals.add(r);
    }

    public static void save_size (Size s){ sizes.add(s); }

    public static ArrayList<Cloth> get_cloths(){
        return cloths;
    }

    public static ArrayList<Rental> get_rentals(){
        return rentals;
    }

    public static ArrayList<Size> get_sizes(){
        return sizes;
    }

    public static void delete_cloth(Cloth cloth){
        for (int i = 0; i < cloths.size(); i++) {
            if(cloths.get(i).getRef().equals(cloth.getRef())){
                cloths.remove(i);
                break;
            }
        }
    }

    public static void delete_rental(Rental rental){
        for (int i = 0; i < rentals.size(); i++) {
            if(rentals.get(i).getDate_return().equals(rental.getDate_return())){
                rentals.remove(i);
                break;
            }
        }
    }
 }
