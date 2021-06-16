package com.example.quiznation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cities extends AppCompatActivity {
    private static final String LOG_TAG = Cities.class.getSimpleName();
    public TextView secondTextView;
    List<CitiesModel> citiesModelList = new ArrayList<>();
    private RecyclerView citiesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        secondTextView = findViewById(R.id.second_textView);


        try {
            getCitiesData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromRawFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.cities);
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
        Log.d(LOG_TAG, "Getting the data");
        return writer.toString();

    }

    private void getCitiesData(String cityJSON) {
        Gson gson = new Gson();
        citiesModelList = gson.fromJson(cityJSON, new TypeToken<ArrayList<CitiesModel>>() {}.getType());
        displayData(getRandomQuestion());
    }


    private CitiesModel getRandomQuestion(){
        Collections.shuffle(citiesModelList,new Random());
        return citiesModelList.get(0);
    }


    private void displayData(CitiesModel citiesModel) {
        String string ="";
        try {
            InputStream inputStream = getAssets().open("CitiesFacts.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondTextView.setText(citiesModel.getCitiesFacts());

    }
    public void showAnotherCityData(View view){
        getAnotherCityData();
    }

    private void getAnotherCityData() {
        Collections.shuffle(citiesModelList,new SecureRandom());
        displayData(citiesModelList.get(0));
    }
}