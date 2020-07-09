package com.example.ibm_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class intent_demo extends AppCompatActivity {

    Button whatsapp,sms,call_new,email;
    EditText number,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_demo);

        //buttons
        whatsapp=findViewById(R.id.send_whatsapp);
        sms=findViewById(R.id.send_sms);
        call_new=findViewById(R.id.make_call);
        email=findViewById(R.id.send_email);

        //edittext
        number=findViewById(R.id.phone);
        mail=findViewById(R.id.mail_field);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setData(Uri.parse("mailto:sanchitgupta187@gmail.com"));
//                i.setType("message/rfc822");
//               // i.putExtra(Intent.EXTRA_EMAIL,new String[]{"sanchitgupta187@gmail.com"});
//                i.putExtra(Intent.EXTRA_SUBJECT,"DEMO");
//                i.putExtra(Intent.EXTRA_TEXT,"This is a sample email content");
//                startActivity(i);
                if(mail.getText().toString().equals("")){
                    Toast.makeText(intent_demo.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mailto = "mailto:"+mail.getText().toString() +
                        "?cc=" + "" +
                        "&subject=" + Uri.encode("subject") +
                        "&body=" + Uri.encode("bodyText");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                    startActivity(emailIntent);
            }

        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>22){
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)!=
                            PackageManager.PERMISSION_GRANTED) {
                        //permission not granted
                        ActivityCompat.requestPermissions(intent_demo.this, new String[]{Manifest.permission.SEND_SMS}, 102);
                    }else {
                        send_sms(number);
                    }
                }
            }
        });

        call_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>22){
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!=
                    PackageManager.PERMISSION_GRANTED) {
                        //permission not granted
                        ActivityCompat.requestPermissions(intent_demo.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    }else {
                        makecall(number);
                    }
                }
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number.getText().toString().equals("")){
                    Toast.makeText(intent_demo.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+number.getText().toString() +"&text="+"This is demo text"));
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(intent_demo.this, "Whatsapp Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void send_sms(EditText number) {
        if(number.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        String message = "Hello";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number.getText().toString(),null,message,null,null);

//        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        smsIntent.putExtra("address",number.getText().toString());
//        smsIntent.putExtra("sms_body","Hello");
//        smsIntent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(smsIntent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==101){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makecall(number);
            }else{
                Toast.makeText(this, "Permission denied can't proceed further", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==102){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                send_sms(number);
            }else{
                Toast.makeText(this, "Permission denied can't proceed further", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makecall(EditText number) {
        if(number.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i =new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+number.getText().toString()));
        startActivity(i);
    }
}