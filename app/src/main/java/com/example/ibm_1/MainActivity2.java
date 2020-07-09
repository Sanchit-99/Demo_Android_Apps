package com.example.ibm_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Toolbar tb;
    EditText id,pass;
    Button login,next,camera,gallery;
    RelativeLayout bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tb=findViewById(R.id.tlbr);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Login Page");
        tb.setTitleTextColor(0xFFFFFFFF);
        id=findViewById(R.id.edt_id_relative);
        pass=findViewById(R.id.edt_pass_relative);
        login=findViewById(R.id.btn_login_relative);
        next=findViewById(R.id.btn_next_screen_relative);
        camera=findViewById(R.id.camera);
        gallery=findViewById(R.id.gallery);
        bg=findViewById(R.id.login_bg);

        //TAKING PERMISSIONS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            camera.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, 0);
        }

        //ONCLICK CAMERA
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,101);
            }
        });

        //ONCLICK GALLERY
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                i.putExtra("return_data",true);
                startActivityForResult(i,102);
            }
        });

//        Intent i=getIntent();
//        Bundle b = i.getExtras();
//        String username = b.getString("username");
//        String password = b.getString("password");
//        id.setText(username);
//        pass.setText(password);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity2.this, "Welcome " + id.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(MainActivity2.this,Register.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                camera.setEnabled(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            Bitmap b= (Bitmap) data.getExtras().get("data");
            bg.setBackground(new BitmapDrawable(getResources(), b));
        }else if(requestCode==102){
            Uri image=data.getData();
            Bitmap b;
            try {
                b = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                bg.setBackground(new BitmapDrawable(getResources(), b));
            }catch (Exception e){
            }
        }
    }
}