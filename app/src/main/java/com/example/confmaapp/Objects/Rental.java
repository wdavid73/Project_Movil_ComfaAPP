package com.example.confmaapp.Objects;

import java.io.Serializable;

public class Rental implements Serializable {
    private String date_now;
    private String date_return;
    private String price;
    private Cloth cloth;

    public Rental(String date_now, String date_return, String price, Cloth cloth) {
        this.date_now = date_now;
        this.date_return = date_return;
        this.price = price;
        this.cloth = cloth;
    }

    public String getDate_now() {
        return date_now;
    }

    public void setDate_now(String date_now) {
        this.date_now = date_now;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    public void save(){
        Data.save_rental(this);
    }

    public void delete(){
        Data.delete_rental(this);
    }
}
