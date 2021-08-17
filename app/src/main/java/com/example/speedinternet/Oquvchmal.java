package com.example.speedinternet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Oquvchmal extends AppCompatActivity {
    DBHelper myDB;
    private Spinner spinner;
    private TextView textView;
    private ImageButton btnqoshbo,btnsaqlalis;
    private EditText lisqosh,tartibb;
    ListView lv;
    Vibrator vibrator;
    ArrayList<String> arrayList;
    String uriString;
    String[] sinflar = {"1- A sinf","1- B sinf","1- D sinf","2- A sinf","2- B sinf","2- d sinf",
            "3- A sinf","3- B sinf","3- D sinf","4- A sinf","4- B sinf","4- D sinf",
            "5- A sinf","5- B sinf","5- D sinf","6- A sinf","6- B sinf","6- D sinf",
            "7- A sinf","7- B sinf","7- D sinf","8- A sinf","8- B sinf","8- D sinf",
            "9- A sinf","9- B sinf","9- D sinf","10- A sinf","10- B sinf","10- D sinf",
            "11- A sinf","11- B sinf","11- D sinf"};


    ArrayAdapter<String> adapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.oquvchimalu);
        myDB = new DBHelper(this);

        lv = (ListView) findViewById(R.id.lv);
        btnqoshbo = findViewById(R.id.btnqoshbo);
        lisqosh = findViewById(R.id.lisqosh);
        btnsaqlalis = findViewById(R.id.btnsaqlalis);
        spinner  = findViewById(R.id.spinner);
        textView = findViewById(R.id.textsin);
        tartibb = findViewById(R.id.tartibb);
        registerForContextMenu(lv);

        arrayList = new ArrayList<>();

        listbaza();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,sinflar);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(sinflar[position]);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tugun);
                textView.startAnimation(animation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnqoshbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnqoshbo.setVisibility(View.INVISIBLE);
                btnsaqlalis.setVisibility(View.VISIBLE);
                lisqosh.setVisibility(View.VISIBLE);
                lv.setVisibility(View.INVISIBLE);
                tartibb.setVisibility(View.VISIBLE);

                }
        });
        btnsaqlalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bazagayoz();
                btnsaqlalis.setVisibility(View.INVISIBLE);
                btnqoshbo.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);
                tartibb.setVisibility(View.INVISIBLE);
                lisqosh.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                vibratt();
                Intent intent = new Intent(Oquvchmal.this,Oquvchmal.class);
                startActivity(intent);
                finish();

                }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("_________");
        menu.add(0,v.getId(),0,"o`chirish");
        menu.add(0,v.getId(),0,"tahrirlash");
        menu.add(0,v.getId(),0,"belgilash");
        menu.add(0,v.getId(),0,"ma`lumot");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "o`chirish"){

        }
        else if (item.getTitle() == "tahrirlash"){

        }
        else if (item.getTitle() == "belgilash"){

        }
        else if (item.getTitle() == "ma`lumot"){

        }
        return true;
    }
    private void vibratt(){
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT>=26){
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else {
            vibrator.vibrate(300);
        }
    }
    private void bazagayoz(){
        String yozuv = lisqosh.getText().toString();
        //String raqam = tartibb.getText().toString();
        Boolean result = myDB.kiritish(yozuv);
        if (result == true){
            Toast.makeText(Oquvchmal.this, "ok", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Oquvchmal.this, "no", Toast.LENGTH_SHORT).show();
        }
    }
    private void listbaza(){
        Cursor cursor = myDB.oqish();
        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("O`rni - "+cursor.getString(0)+"\n");
                stringBuffer.append("ismi - "+cursor.getString(1));
                arrayList.add(String.valueOf(stringBuffer));

            }

        }
    }

}
