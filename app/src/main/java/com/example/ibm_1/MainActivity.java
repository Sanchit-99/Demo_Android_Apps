package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar tb;
    EditText id,pass;
    Button login_btn,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb=findViewById(R.id.tlbr);
        id=findViewById(R.id.edt_id_linear);
        pass=findViewById(R.id.edt_pass_linear);
        login_btn=findViewById(R.id.btn_login_linear);
        next=findViewById(R.id.btn_next_screen_linear);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Login Page");
        tb.setTitleTextColor(0xFFFFFFFF);




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Welcome "+id.getText().toString() , Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
}