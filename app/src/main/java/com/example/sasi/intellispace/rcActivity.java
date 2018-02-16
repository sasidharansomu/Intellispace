package com.example.sasi.intellispace;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;

import java.util.ArrayList;

public class rcActivity extends AppCompatActivity
{

    public ArrayList<CardAdapter> itemCardAdapter =new ArrayList<>();
    public ItemAdapter itemArrayAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Spinner BuildingSpinnner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_activity);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        new Spinner_Item_Task().execute();

        itemCardAdapter.add(0,new CardAdapter("bow","bow","bow"));
        itemArrayAdapter = new ItemAdapter(R.layout.building_card, itemCardAdapter);
        recyclerView.setAdapter(itemArrayAdapter);
    }
    public class Spinner_Item_Task extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }
    }
}
