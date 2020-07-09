package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class back_pressed extends AppCompatActivity {
    Boolean exit=false;
    ListView listView;
    String[] listitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_pressed);
        listView=findViewById(R.id.lst);
        listitem=getResources().getStringArray(R.array.dummy_data);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,listitem);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(back_pressed.this, arrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        if(exit==false){
            exit=true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            return;
        }else{
            super.onBackPressed();
        }

    }
}