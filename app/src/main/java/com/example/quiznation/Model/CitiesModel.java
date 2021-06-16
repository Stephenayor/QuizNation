package com.example.quiznation.Model;

import com.google.gson.annotations.SerializedName;

public class CitiesModel {
    @SerializedName("id")
    private int id;
    @SerializedName("citiesInfo")
    private String citiesFacts;

    public CitiesModel(int id, String citiesFacts) {
        this.id = id;
        this.citiesFacts = citiesFacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitiesFacts() {
        return citiesFacts;
    }

    public void setCitiesFacts(String citiesFacts) {
        this.citiesFacts = citiesFacts;
    }
}
