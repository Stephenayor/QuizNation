package com.example.quiznation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Textview for Activities
        TextView country_vw = (TextView) findViewById(R.id.country_vw);
        TextView capitals_vw = (TextView) findViewById(R.id.capitals_vw);
        TextView cities_vw = (TextView) findViewById(R.id.cities_vw);

        assert  country_vw !=null;
        country_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countriesIntent = new Intent(MainActivity.this, Countries.class);
                startActivity(countriesIntent);
            }
        });

        assert  capitals_vw !=null;
        capitals_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent capitalsIntent = new Intent(MainActivity.this, Capitals.class);
                startActivity(capitalsIntent);
            }
        });

        assert cities_vw !=null;
        cities_vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent citiesIntent = new Intent(MainActivity.this, Cities.class);
                startActivity(citiesIntent);
            }
        });
    }
}