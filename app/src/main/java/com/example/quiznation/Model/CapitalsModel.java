package com.example.quiznation.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CapitalsModel {
    @SerializedName("id")
    private int id;
    @SerializedName("capital")
    private String countryCapital;
    @SerializedName("country")
    private String countryName;
    @SerializedName("options")
    private List<String> optionList;

    public CapitalsModel(int id, String countryCapital, String countryName, List<String> optionList) {
        this.id = id;
        this.countryCapital = countryCapital;
        this.countryName = countryName;
        this.optionList = optionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
}
