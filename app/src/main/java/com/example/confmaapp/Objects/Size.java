package com.example.confmaapp.Objects;

import java.io.Serializable;

public class Size implements Serializable {

    private String name;
    private String type;

    public Size(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void save(){
        Data.save_size(this);
    }
}
