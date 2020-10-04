package com.example.jokes;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jokes.Adapter.AdapterWeb;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ApiInfo extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private WebView webView;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_info);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottom();
        url="http://www.icndb.com/api";
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        if (savedInstanceState!=null){
            url=savedInstanceState.getString("url");
        }
        webView.loadUrl(url);
        webView.setWebViewClient(new AdapterWeb());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("url",url);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private void bottom() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(ApiInfo.this, MainActivity.class);
                Intent intent1 = new Intent(ApiInfo.this, ApiInfo.class);
                switch (item.getItemId()) {
                    case R.id.jokes_but:
                        startActivity(intent);
                        break;
                    case R.id.web_but:
                        startActivity(intent1);
                        break;
                }
                return false;
            }
        });
    }
}
