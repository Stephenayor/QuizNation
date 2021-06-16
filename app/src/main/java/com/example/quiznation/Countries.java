package com.example.quiznation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Countries extends AppCompatActivity implements CountryOptionsListAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private ImageView imageView;
    private List<CountryModel> countryModelList = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();
    private Integer scoreTextView;
    private TextView textView;
    private CountryModel countryModel;
    //CountryModel countryModel = new CountryModel(1, "Nigeria", "nigeria.png", stringList);
    private int keepTheObjectID;
    Context mContext;
    List<Integer> imageDisplayedID = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        imageView = findViewById(R.id.countries_flag_image);
        recyclerView = findViewById(R.id.options_recyclerView);
        scoreTextView = 3;
        textView = findViewById(R.id.resultText);


        try {
            getCountryData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(scoreTextView.toString());
    }

    private String readFromRawFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.countries);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            inputStream.close();
        }
        return writer.toString();
    }


    private void getCountryData(String countryJson) {
        Gson gson = new Gson();
        countryModelList = gson.fromJson(countryJson, new TypeToken<ArrayList<CountryModel>>() {}.getType());
        displayData(getRandomQuestion());
    }


    private CountryModel getRandomQuestion(){
        Collections.shuffle(countryModelList, new Random());
        return countryModelList.get(0);
    }

    private void displayData(CountryModel countryModel) {
        // USE GLIDE TO LOAD THE IMAGES
        Glide.with(this)
                .load("file:///android_asset/"  + countryModel.getImageName())
                .into(imageView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new CountryOptionsListAdapter(this, this, countryModel));


        keepTheObjectID = countryModelList.get(0).getId();
    }



    @Override
    public void onItemClick(String optionChosen, int position, CountryModel countryModel) {
        if (countryModel.getCorrectAnswer().equals(optionChosen)) {
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
            getAnotherCountryData(countryModel);
        } else {
            Toast.makeText(this, "WRONG", Toast.LENGTH_SHORT).show();
            scoreTextView = scoreTextView - 1;
            textView.setText(scoreTextView.toString());

            if (scoreTextView==0) {
                Toast.makeText(this, "SORRY, KEEP ON PRACTISING", Toast.LENGTH_SHORT).show();
                this.finish();
            }

            getAnotherCountryData(countryModel);
        }
    }




    private void getAnotherCountryData(CountryModel countryModel) {
        Collections.shuffle(countryModelList, new Random());
        if (countryModelList.get(0).id==keepTheObjectID){
            Collections.shuffle(countryModelList,new Random());
            displayData(countryModelList.get(0));

        }else {

            displayData(countryModelList.get(0));
        }

    }
}