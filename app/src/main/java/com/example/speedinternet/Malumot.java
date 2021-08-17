package com.example.speedinternet;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Malumot extends AppCompatActivity {

    private long pressedTime;
    private Toast toast1;
    private RelativeLayout.LayoutParams layoutParams;
    private Button aloqa;
    private TextView textView,textqu;
    private ImageView imageoy;
    String mobilnumer ;

    BroadcastReceiver broadcastReceiver;
    String massage;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malumot);

        aloqa = findViewById(R.id.aloqa);
        textView = findViewById(R.id.textView5);
        imageoy = findViewById(R.id.imageoy);
        textqu = findViewById(R.id.textquvvat);

        quvvat();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver,intentFilter);

        imageoy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTyes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragdata = new ClipData(v.getTag().toString(),mimeTyes, item);
                View.DragShadowBuilder myShadov = new View.DragShadowBuilder(imageoy);

                v.startDrag(dragdata,myShadov,null,0);
                return true;
            }
        });

        imageoy.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                       layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                       Log.d(massage,"bjbjbjbjb");
                       break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(massage,"bjbjbj");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            Log.d(massage,"bjbjbjb");
                            x_cord = (int) event.getX();
                            y_cord = (int)event.getY();
                            layoutParams.leftMargin = x_cord;
                            v.setLayoutParams(layoutParams);
                            break;
                            case DragEvent.ACTION_DRAG_LOCATION:
                                Log.d(massage, "bjbjbbjbj");
                                x_cord = (int) event.getY();
                                y_cord = (int) event.getY();
                                break;
                                case DragEvent.ACTION_DRAG_ENDED:
                                    Log.d(massage,"bjbjbjbjbjbjb");
                                    break;
                                    case  DragEvent.ACTION_DROP:
                                        Log.d(massage, "bjbjbjbjb");
                                        break;
                    default: break;
                }
                return true;
            }
        });
        imageoy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageoy);
                    imageoy.startDrag(data,shadowBuilder,imageoy, 0);
                    imageoy.setVisibility(View.INVISIBLE);
                    //vibratt1();
                    return true;
                }else {
                    return false;
                }

            }
        });


        aloqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(aloqa);
                Toast.makeText(Malumot.this, "tugmani uzoqroq bosib turing", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            imageoy.setVisibility(View.VISIBLE);
            Toast.makeText(getBaseContext(), "Chiqish uchun ikki marta bosing", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
    private void quvvat(){
        broadcastReceiver = new BroadcastReceiver() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context context, Intent intent) {
                int level1 = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                textqu.setText("tel quvvati: "+Integer.toString(level1)+"%");

            }
        };
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("_________");
        menu.add(0,v.getId(),0,"qo`ng`iroq qilish");
        menu.add(0,v.getId(),0,"xabar yozish");
        menu.add(0,v.getId(),0,"telegramdan murojat");
        menu.add(0,v.getId(),0,"instagramdan murojat");

    }

    @Override
    public boolean onContextItemSelected( MenuItem item) {
        if (item.getTitle() == "qo`ng`iroq qilish"){
            String numer = textView.getText().toString();
            Intent geintent = new Intent(Intent.ACTION_CALL);
            geintent.setData(Uri.parse("tel: "+numer));
            startActivity(geintent);
        }
        else if (item.getTitle() == "xabar yozish"){

        }
        else if (item.getTitle() == "telegramdan murojat"){

        }
        else if (item.getTitle() == "instagramdan murojat"){

        }
        return true;
    }
    private void vibratt1(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT>=26){
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else {
            vibrator.vibrate(300);
        }
    }
}
