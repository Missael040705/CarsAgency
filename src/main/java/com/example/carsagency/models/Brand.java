package com.example.carsagency.models;

public class Brand {
    private int id_Brand;
    private String name;

    public Brand(){

    }

    public Brand(int id_Brand, String name){
        this.id_Brand = id_Brand;
        this.name = name;
    }

    public int getId_Brand() {
        return id_Brand;
    }

    public void setId_Brand(int id_Brand) {
        this.id_Brand = id_Brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
