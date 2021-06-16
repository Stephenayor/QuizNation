package com.example.quiznation;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryModel {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String correctAnswer;
    @SerializedName("image")
    public String imageName;
    @SerializedName("options")
    private List<String> optionList;


    public CountryModel(int id,String correctAnswer, String imageName, List<String> optionList) {
        this.id=id;
        this.correctAnswer = correctAnswer;
        this.imageName=imageName;
        this.optionList=optionList;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
}
