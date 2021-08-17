package com.example.speedinternet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class Asosiy extends AppCompatActivity {
    String sum;

    private ImageButton btnoqt,btnoqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asosiy);

        btnoqt = findViewById(R.id.btnoqt);
        btnoqu = findViewById(R.id.btnoqu);
        setTitle("15 - Maktab");

        btnoqt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asosiy.this,oqtuvchmal.class);
                startActivity(intent);
            }
        });
        btnoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asosiy.this,Oquvchmal.class);
                startActivity(intent);
            }
        });

    }
    @Override public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Ilovadan chiqish") .setMessage("Ilovadan chiqishni xohlaysizmi?")
                .setPositiveButton("Ha", new DialogInterface.OnClickListener()
                { @Override public void onClick(DialogInterface dialog, int which)
                { finish(); } }) .setNegativeButton("Yo'q", null)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menyu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent4 = new Intent(Asosiy.this,Kirish.class);
                startActivity(intent4);
                return true;
                case R.id.item5:
                Intent intent5 = new Intent(Asosiy.this,Dars.class);
                startActivity(intent5);
                return true;
            case R.id.item2:
                Intent intent3 = new Intent(Asosiy.this,Murojat.class);
                startActivity(intent3);
                return true;
            case R.id.item3:
                Intent intent1 = new Intent(Asosiy.this,Sozlamalar.class);
                startActivity(intent1);
                return true;
            case R.id.item4:
                Intent intent = new Intent(Asosiy.this,Malumot.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
