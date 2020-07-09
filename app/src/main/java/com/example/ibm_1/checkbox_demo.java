package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class checkbox_demo extends AppCompatActivity {

    CheckBox android,java,python,angular;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_demo);

        android=findViewById(R.id.cb_ad);
        java=findViewById(R.id.cb_java);
        python=findViewById(R.id.cb_py);
        angular=findViewById(R.id.cb_an);

        submit=findViewById(R.id.cb_submit);

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(android.isChecked())
                Toast.makeText(checkbox_demo.this, android.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(java.isChecked())
                Toast.makeText(checkbox_demo.this, java.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(python.isChecked())
                Toast.makeText(checkbox_demo.this, python.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
        angular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(angular.isChecked())
                Toast.makeText(checkbox_demo.this, angular.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result="";
                if(android.isChecked()){
                    result+=" " + android.getText().toString();
                }
                if(java.isChecked()){
                    result+=" " + java.getText().toString();
                }
                if(python.isChecked()){
                    result+=" " + python.getText().toString();
                }
                if(angular.isChecked()){
                    result+=" " + angular.getText().toString();
                }
                Toast.makeText(checkbox_demo.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}