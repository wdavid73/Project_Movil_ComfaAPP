package com.example.confmaapp.Objects;

import android.net.Uri;

import java.io.Serializable;

public class Cloth implements Serializable {
    private Uri photo_cloth;
    private String ref;
    private int size;
    private int color;
    private int style_fashion;

    public Cloth(Uri photo_cloth, String ref, int size, int color, int style_fashion) {
        this.photo_cloth = photo_cloth;
        this.ref = ref;
        this.size = size;
        this.color = color;
        this.style_fashion = style_fashion;
    }

    public Uri getPhoto_cloth() {
        return photo_cloth;
    }

    public void setPhoto_cloth(Uri photo_cloth) {
        this.photo_cloth = photo_cloth;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStyle_fashion() {
        return style_fashion;
    }

    public void setStyle_fashion(int style_fashion) {
        this.style_fashion = style_fashion;
    }

    public void save(){
        Data.save_cloth(this);
    }
}
