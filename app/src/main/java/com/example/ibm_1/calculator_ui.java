package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class calculator_ui extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    final int ADD=0,DIVIDE=1,MULTIPLY=2,SUBTRACT=3;
    int operator;
    int pos;
    String input="";
    Button one,two,three,four,five,six,seven,eight,nine,erase,zero,add,divide,multiply,subtract,equals,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_ui);
        result=findViewById(R.id.result_view);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        erase=findViewById(R.id.erase);
        zero=findViewById(R.id.zero);
        add=findViewById(R.id.add);
        divide=findViewById(R.id.divide);
        multiply=findViewById(R.id.multiply);
        subtract=findViewById(R.id.subtract);
        equals=findViewById(R.id.equals);
        delete=findViewById(R.id.delete);


        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        erase.setOnClickListener(this);
        zero.setOnClickListener(this);
        add.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        subtract.setOnClickListener(this);
        equals.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.one:
                input+="1";
                result.setText(input);
                break;
            case R.id.two:
                input+="2";
                result.setText(input);
                break;
            case R.id.three:
                input+="3";
                result.setText(input);
                break;
            case R.id.four:
                input+="4";
                result.setText(input);
                break;
            case R.id.five:
                input+="5";
                result.setText(input);
                break;
            case R.id.six:
                input+="6";
                result.setText(input);
                break;
            case R.id.seven:
                input+="7";
                result.setText(input);
                break;
            case R.id.eight:
                input+="8";
                result.setText(input);
                break;
            case R.id.nine:
                input+="9";
                result.setText(input);
                break;
            case R.id.zero:
                input+="0";
                result.setText(input);
                break;
            case R.id.erase:
                if(input.isEmpty())
                    break;
                input=input.substring(0, input.length() - 1);
                result.setText(input);
                break;
            case R.id.add:
                operator=ADD;
                pos=input.length();
               try {
                   input += "\u002B";
               }
               catch (Exception e){

               }
                result.setText(input);
                break;
            case R.id.divide:
                operator=DIVIDE;
                pos=input.length();
                input+="/";
                result.setText(input);
                break;
            case R.id.multiply:
                operator=MULTIPLY;
                pos=input.length();
                input+="*";
                result.setText(input);
                break;
            case R.id.subtract:
                operator=SUBTRACT;
                pos=input.length();
                input+="-";
                result.setText(input);
                break;
            case R.id.equals:
                if(input.isEmpty())
                    break;
               performOperation();
                break;
            case R.id.delete:
                if(input.isEmpty())
                    break;
                input="";
                result.setText(input);
                break;
        }
    }
    private void performOperation() {
        int op1=Integer.parseInt(input.substring(0,pos));
        int op2=Integer.parseInt(input.substring(pos+1,input.length()));
        if(operator==ADD){
            int ans=op1+op2;
            result.setText(Integer.toString(ans));
            input=Integer.toString(ans);
        }
        if(operator==MULTIPLY){
            int ans=op1*op2;
            result.setText(Integer.toString(ans));
            input=Integer.toString(ans);
        }
        if(operator==DIVIDE){
            float ans=(float)op1/op2;
            result.setText(Float.toString(ans));
            input=Float.toString(ans);
        }
        if(operator==SUBTRACT){
            int ans=op1-op2;
            result.setText(Integer.toString(ans));
            input=Integer.toString(ans);
        }
    }
}