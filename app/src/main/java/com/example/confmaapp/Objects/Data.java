package com.example.confmaapp.Objects;

import java.util.ArrayList;

public class Data {

    public static ArrayList<Cloth> cloths = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();

    public static void save_cloth (Cloth c) {
        cloths.add(c);
    }

    public static void save_rental (Rental r){
        rentals.add(r);
    }

    public static ArrayList<Cloth> get_cloths(){
        return cloths;
    }

    public static ArrayList<Rental> get_rentals(){
        return rentals;
    }
 }
