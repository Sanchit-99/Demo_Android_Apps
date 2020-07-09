package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class Swipe extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        swipeRefreshLayout=findViewById(R.id.swipe);
        txt=findViewById(R.id.txt_internet);

        if(isNetworkConnected()){
            txt.setText("INTERNET IS ON");
            txt.setTextColor(Color.GREEN);
        }else{
            txt.setText("INTERNET IS OFF");
            txt.setTextColor(Color.RED);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isNetworkConnected()){
                    txt.setText("INTERNET IS ON");
                    txt.setTextColor(Color.GREEN);
                }else{
                    txt.setText("INTERNET IS OFF");
                    txt.setTextColor(Color.RED);
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}