package com.example.speedinternet;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Dars extends AppCompatActivity {
    private TextView tetx;
    private WebView mWebView;
    String url = ("https://www.youtube.com/channel/UCMY7kxVierN7Nx3q6n2WIzQ");
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dars);
        mWebView = (WebView) findViewById(R.id.veeb);
        tetx = findViewById(R.id.tetx);

       /* WebSettings webSettings = mWebView.getSettings();
        ((WebSettings) webSettings).setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.youtube.com/channel/UCMY7kxVierN7Nx3q6n2WIzQ");*/
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://youtube.com");

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ninfo = cManager.getActiveNetworkInfo();
        if(ninfo!=null && ninfo.isConnected()) {
            mWebView.setVisibility(View.VISIBLE);
        }
        else {
            mWebView.setVisibility(View.INVISIBLE);
            tetx.setVisibility(View.VISIBLE);
        }
    }

}
