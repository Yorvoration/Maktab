package com.example.speedinternet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Kirish extends AppCompatActivity {
    private Button tahrir;
    private static final int CAMERA_REQUEST = 123;
    private ImageView profill;
    private ImageButton btnsaqla,btnkamer,btncoll,btninstagr;
    private EditText editozgar;
    String Filename = "MyFile1";
    String uriString;
    private TextView textView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kirish);
        tahrir = findViewById(R.id.tahrir);
        btnkamer = findViewById(R.id.btnkamer);
        btnsaqla = findViewById(R.id.btnsaqla);
        profill = findViewById(R.id.profilrr);
        //btncoll = findViewById(R.id.btncoll);
       // btninstagr = findViewById(R.id.btninstag);
        editozgar = findViewById(R.id.editozgar);
        textView = findViewById(R.id.nommii);
        nommiii();

        tahrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsaqla.setVisibility(View.VISIBLE);
                btnkamer.setVisibility(View.VISIBLE);
                editozgar.setVisibility(View.VISIBLE);
            }
        });
        btnsaqla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnkamer.setVisibility(View.INVISIBLE);
                btnsaqla.setVisibility(View.INVISIBLE);
                editozgar.setVisibility(View.INVISIBLE);
                yozish();
                nommiii();
            }
        });
        btnkamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
    }
    private void yozish(){
        String dtrname = editozgar.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(Filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",dtrname);
        editor.apply();
        Toast.makeText(this, "saqlandi", Toast.LENGTH_SHORT).show();
    }
    private void nommiii(){
        @SuppressLint("WrongConstant")
        SharedPreferences sharedPref = getSharedPreferences(Filename, Context.MODE_PRIVATE);
        String defaultValue = "Name";
        String name = sharedPref.getString("name",defaultValue);
        textView.setText(name);

    }
    public void onActivityResult(int requestcode, int resultcode, Intent data){
        if (requestcode == CAMERA_REQUEST && resultcode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profill.setImageBitmap(photo);
        }
    }
}
