package com.example.mybudgetcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EgargneActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    TextView no_data;
    ImageView empty_imageview;

    dbHelper myDB;
    ArrayList<String> epargne_id, epargne_cap, epargne_taux, epargne_date, currency;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egargne);

        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);

        empty_imageview = findViewById(R.id.empty_imageview);
        myDB = new dbHelper(EgargneActivity.this);
        epargne_id = new ArrayList<>();
        epargne_cap = new ArrayList<>();
        epargne_taux = new ArrayList<>();
        currency = new ArrayList<>();
        epargne_date = new ArrayList<>();


        storeDataInArrays();

        customAdapter = new CustomAdapter(EgargneActivity.this,this, epargne_id, epargne_cap, epargne_taux,
                epargne_date, currency);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(EgargneActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                epargne_id.add(cursor.getString(0));
                epargne_cap.add(cursor.getString(1));
                epargne_taux.add(cursor.getString(2));
                epargne_date.add(cursor.getString(3));
                currency.add(cursor.getString(4));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}
