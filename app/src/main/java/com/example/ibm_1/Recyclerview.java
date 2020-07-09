package com.example.ibm_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    public Boolean isLinear=true;
    List<data> mydata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();

        recyclerView=findViewById(R.id.recycler_view);
        toolbar=findViewById(R.id.tlbr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RecyclerView Demo");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        mydata = new ArrayList<data>();
        mydata.add( new data("Aashi",R.drawable.female,"8569874523"));
        mydata.add( new data("Sanchit",R.drawable.male,"9807941374"));
        mydata.add( new data("Harsh",R.drawable.male,"9745268542"));
        mydata.add( new data("Prabhakar",R.drawable.male,"8512369514"));
        mydata.add( new data("Akanksha",R.drawable.female,"7856321485"));
        mydata.add( new data("Rishu",R.drawable.male,"7536548219"));
        mydata.add( new data("Sakshi",R.drawable.female,"9696545431"));
        mydata.add( new data("Raghav",R.drawable.male,"8960157443"));
        mydata.add( new data("Arpit",R.drawable.male,"8109019864"));
        mydata.add( new data("Pranjal",R.drawable.male,"9336119819"));
        mydata.add( new data("Ashmita",R.drawable.female,"8007445890"));
        mydata.add( new data("Vishnu",R.drawable.male,"7845236915"));

        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(mydata,this,isLinear);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_selector,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerAdapter recyclerAdapter;
        switch (item.getItemId()){
            case R.id.lnr_hrz:
                 recyclerAdapter=new RecyclerAdapter(mydata,this,false);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                break;
            case R.id.lnr_vrt:
                 recyclerAdapter=new RecyclerAdapter(mydata,this,true);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.grid:
                recyclerAdapter=new RecyclerAdapter(mydata,this,false);
                recyclerView.setAdapter(recyclerAdapter);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}