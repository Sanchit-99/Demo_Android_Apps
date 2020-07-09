package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class searchView_demo extends AppCompatActivity  {

    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String[] data={"Abhinav","Abhishek","Aakash","Ashutosh","Anil","Piyush","Sanchit","Shruti","Sakshi","Sarthak",
    "Suresh","Prabhakar","Rishabh","Arvind","Anmol","Pooja","Krishna","Sameer","Ritesh","Sunil"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view_demo);
        listView=findViewById(R.id.list_view);
        searchView=findViewById(R.id.search_view);

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}