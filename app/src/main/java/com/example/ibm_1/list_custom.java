package com.example.ibm_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class list_custom extends AppCompatActivity {

    Toolbar tb;
    Window window;
    ListView listView;
    RelativeLayout bg;
    CustomAdapter customAdapter;
    Boolean isNightmode=false;
    String[] names={"Shruti","Sakshi","Sarthak",
            "Suresh","Prabhakar","Rishabh","Arvind","Anmol","Pooja","Krishna","Sameer","Ritesh","Sunil"};
    String[] numbers={"1202765651", "4261285581", "2256624575", "2226609851", "2223727866",
            "1125795930", "2224095117", "2652305165", "1612536082", "2228333876", "2226848298", "7925832928", "2228198897"};
    int[] img={R.drawable.female,R.drawable.female,R.drawable.male,R.drawable.male,R.drawable.male,R.drawable.male,
            R.drawable.male,R.drawable.male,R.drawable.female,R.drawable.male,R.drawable.male,R.drawable.male,R.drawable.male};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_custom);
        tb=findViewById(R.id.tlbr);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Demo App");
        tb.setTitleTextColor(Color.WHITE);
        tb.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        listView=findViewById(R.id.custom_list);

        customAdapter =new CustomAdapter();
        listView.setAdapter(customAdapter);

        bg=findViewById(R.id.bg);

        registerForContextMenu(listView);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CALL_PHONE }, 101);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.SEND_SMS }, 102);
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_ctx,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getItemId()==R.id.call){
            call(numbers[info.position]);
        }
        else if(item.getItemId()==R.id.sms){
            message(numbers[info.position]);
        }
        return super.onContextItemSelected(item);
    }

    private void call(String num) {

        if(Build.VERSION.SDK_INT>22){
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!=
                    PackageManager.PERMISSION_GRANTED) {
                //permission not granted
                ActivityCompat.requestPermissions(list_custom.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
            }else {
                Intent i =new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+num));
                startActivity(i);
            }
        }

    }
    private void message(String num) {
        if(Build.VERSION.SDK_INT>22){
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)!=
                    PackageManager.PERMISSION_GRANTED) {
                //permission not granted
                ActivityCompat.requestPermissions(list_custom.this, new String[]{Manifest.permission.SEND_SMS}, 102);
            }else {
                String message = "Hello";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num,null,message,null,null);
            }
        }
    }

    class CustomAdapter extends BaseAdapter{
        public CustomAdapter() {
            super();
        }
        @Override
        public int getCount() {
            return names.length;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater =  getLayoutInflater();

                convertView = inflater.inflate(R.layout.listview_layout, null,true);
                TextView txtViewTitle =  convertView.findViewById(R.id.name);
                TextView txtViewDescription =  convertView.findViewById(R.id.num);
                ImageView imageView=convertView.findViewById(R.id.img);
                CardView  back=convertView.findViewById(R.id.back);

                txtViewTitle.setText(names[position]);
                txtViewDescription.setText(numbers[position]);
                imageView.setImageResource(img[position]);

                if(isNightmode){
                    back.setCardBackgroundColor(getResources().getColor(R.color.arsenic));
                    txtViewTitle.setTextColor(Color.WHITE);
                    txtViewDescription.setTextColor(Color.WHITE);
                }else{
                    back.setCardBackgroundColor(Color.WHITE);
                    txtViewTitle.setTextColor(getResources().getColor(R.color.txt));
                    txtViewDescription.setTextColor(getResources().getColor(R.color.txt));
                }


            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.calc:
                Intent i=new Intent(getApplicationContext(),calculator_ui.class);
                startActivity(i);
                break;
            case R.id.exit:
                finish();
                break;
            case R.id.light:
                isNightmode=false;
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
                tb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bg.setBackgroundColor(Color.WHITE);
                customAdapter.notifyDataSetChanged();
                break;
            case R.id.dark:
                isNightmode=true;
                tb.setBackgroundColor(getResources().getColor(R.color.arsenic));
                window.setStatusBarColor(ContextCompat.getColor(this,R.color.arsenic));                bg.setBackgroundColor(Color.WHITE);
                bg.setBackgroundColor(getResources().getColor(R.color.dark));
                customAdapter.notifyDataSetChanged();
                break;
            case R.id.cng_bg:
                Intent in=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(in);

        }
        return super.onOptionsItemSelected(item);
    }

}