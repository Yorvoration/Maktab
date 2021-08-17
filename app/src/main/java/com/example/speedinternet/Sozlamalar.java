package com.example.speedinternet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.speedinternet.R.color.black;
import static com.example.speedinternet.R.color.primary_dark_material_dark;
import static com.example.speedinternet.R.color.white;

public class Sozlamalar extends AppCompatActivity {

    private Switch tunrej;
    private RelativeLayout layout;
    private TextView textView;
    private Spinner til;
    private SeekBar seekBar;
    String[] tillar = {"O`zbekcha","Englsh","Russian"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sozlamalar);
        tunrej = findViewById(R.id.tunrej);
        textView = findViewById(R.id.kattaliktxt);
        layout = findViewById(R.id.ssssss);
        til = findViewById(R.id.til);
        seekBar = findViewById(R.id.seekBar);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int min =4,max=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress , boolean fromUser) {
                max = min*progress;
                textView.setTextSize(max);
                tunrej.setTextSize(max);
                seekBar.setTextAlignment(max);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setTextSize(max);
                tunrej.setTextSize(max);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setTextSize(max);
                tunrej.setTextSize(max);
            }

        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,tillar);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        til.setAdapter(adapter1);

        tunrej.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tunrej.isChecked()){
                    Toast.makeText(Sozlamalar.this, "tungi rejim yoqildi", Toast.LENGTH_SHORT).show();
                    layout.setBackgroundResource(black);
                    tunrej.setTextColor(R.color.oq);
                    tunrej.setBackgroundResource(black);
                    textView.setTextColor(R.color.white);
                    textView.setBackgroundResource(black);
                }
                else {
                    layout.setBackgroundResource(R.color.white);
                    textView.setBackgroundResource(R.color.white);
                    tunrej.setBackgroundResource(R.color.white);
                    tunrej.setTextColor(black);
                    textView.setTextColor(black);
                }
            }
        });

    }

}
