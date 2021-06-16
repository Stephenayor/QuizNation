package com.example.quiznation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiznation.Adapter.CapitalOptionsListAdapter;
import com.example.quiznation.Model.CapitalsModel;
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

public class Capitals extends AppCompatActivity implements CapitalOptionsListAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private  RecyclerView countryNameRecyclerView;
    private CapitalsModel capitalsModel;
    private TextView textView;
    private  int keepTheObjectID;
    List<CapitalsModel> capitalsModelList = new ArrayList<>();
    private Integer scoreView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitals);
        recyclerView = (RecyclerView) findViewById(R.id.capitals_list_recyclerView);
        textView = findViewById(R.id.textView);
        scoreView = 3;
        resultTextView = findViewById(R.id.resultText);


        try {
            getCapitalData(readFromRawFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultTextView.setText(scoreView.toString());
    }


    private String readFromRawFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.capitals);
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


    private void getCapitalData(String capitalsJSON) {
        Gson gson = new Gson();
        capitalsModelList = gson.fromJson(capitalsJSON, new TypeToken<ArrayList<CapitalsModel>>() {}.getType());
        displayData(getRandomQuestion());
    }


    private CapitalsModel getRandomQuestion(){
        Collections.shuffle(capitalsModelList,new Random());
        return capitalsModelList.get(0);
    }



    private void displayData(CapitalsModel capitalsModel) {
        String string ="";
        try {
            InputStream inputStream = getAssets().open("CountryNamesText.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText(capitalsModel.getCountryName());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new CapitalOptionsListAdapter(this,this,capitalsModel));

        keepTheObjectID = capitalsModelList.get(0).getId();
    }



    @Override
    public void onItemClick(String optionChosen, int position, CapitalsModel capitalsModel) {
        boolean answerChecker = capitalsModel.getCountryCapital().equals(optionChosen);
        if (capitalsModel.getCountryCapital().equals(optionChosen)){
            Toast.makeText(this,"CORRECT",Toast.LENGTH_SHORT).show();
            getAnotherCapitalData();
        }else{
            Toast.makeText(this,"WRONG",Toast.LENGTH_SHORT).show();
            scoreView = scoreView - 1;
            resultTextView.setText(scoreView.toString());

            if (scoreView==0) {
                Toast.makeText(this, "SORRY, KEEP ON PRACTISING", Toast.LENGTH_SHORT).show();
                this.finish();
            }
            getAnotherCapitalData();
        }
    }

    private void getAnotherCapitalData(){
        Collections.shuffle(capitalsModelList, new Random());
        if (capitalsModelList.get(0).getId()==keepTheObjectID){
            Collections.shuffle(capitalsModelList,new Random());
            displayData(capitalsModelList.get(0));
        }
        else {
            displayData(capitalsModelList.get(0));

        }
    }

}
