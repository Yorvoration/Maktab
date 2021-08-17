package com.example.speedinternet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class oqtuvchmal extends AppCompatActivity {
    ListView lv1;
    String ul;
    String[] oqtuvchilar = {"Direktor"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oqtuvchmal);
        lv1 = (ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,oqtuvchilar);
        lv1.setAdapter(adapter1);
    }
}
