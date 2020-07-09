package com.example.ibm_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    RadioGroup rg_mode,rg_gender;
    Toolbar tb;
    RelativeLayout relativeLayout;
    Button register_btn,btn,call;
    EditText edt_username,edt_password,edt_email,edt_phone;
    String Gender="";
    CheckBox cb_android,cb_python,cb_angular,cb_react;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        relativeLayout=findViewById(R.id.relative_register);
        rg_mode=findViewById(R.id.rg_mode);
        rg_gender=findViewById(R.id.rg_gender);
        tb=findViewById(R.id.tlbr);
        register_btn=findViewById(R.id.register_btn);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        edt_email=findViewById(R.id.edt_email);
        edt_phone=findViewById(R.id.edt_phone_no);
        cb_android=findViewById(R.id.cb_android);
        cb_angular=findViewById(R.id.cb_angular);
        cb_python=findViewById(R.id.cb_python);
        cb_react=findViewById(R.id.cb_react);
        call=findViewById(R.id.call);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Registration Page");
        tb.setTitleTextColor(0xFFFFFFFF);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9807941374"));
                if(intent.resolveActivity(getPackageManager())!=null);
                startActivity(intent);
            }
        });

        LayoutInflater layoutInflater = getLayoutInflater();
        final View layout = layoutInflater.inflate(R.layout.custo_toast,(ViewGroup)findViewById(R.id.root_toast));

        TextView toasttextview = layout.findViewById(R.id.cust_text);
        ImageView toastimageview = layout.findViewById(R.id.cust_image);

       //open website btn code
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/"));

                startActivity(Intent.createChooser(intent,"Open website using..."));

            }
        });

        rg_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton rb = findViewById(id);
                if(rb.getTag().equals("d")){
                    relativeLayout.setBackgroundColor(Color.GRAY);
                    tb.setBackgroundColor(Color.BLACK);
                    tb.setTitleTextColor(Color.WHITE);
                    register_btn.setBackgroundColor(Color.BLACK);
                }else {
                    tb.setBackgroundColor(Color.parseColor("#6200EE"));
                    tb.setTitleTextColor(Color.WHITE);
                    relativeLayout.setBackgroundColor(Color.WHITE);
                    register_btn.setBackgroundColor(Color.parseColor("#6200EE"));
                }
            }
        });
        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                if(rb!=null) {
                    if (rb.getText().equals("Male")) {
                        Gender="Male";
                    } else {
                        Gender="Female";
                    }
                }
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkbox_state() || edt_username.getText().toString().isEmpty() || edt_email.getText().toString().isEmpty() ||
                edt_password.getText().toString().isEmpty() || edt_phone.getText().toString().isEmpty() || Gender.equals("")){
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    //Toast.makeText(Register.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {
                    Intent i=new Intent(getApplicationContext(),MainActivity2.class);
                    Bundle b=new Bundle();
                    b.putString("username",edt_username.getText().toString());
                    b.putString("password",edt_password.getText().toString());
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });

    }

    private Boolean checkbox_state() {
        if(cb_react.isChecked() || cb_angular.isChecked() || cb_python.isChecked() || cb_android.isChecked()){
            return false;
        }
        return true;
    }
}