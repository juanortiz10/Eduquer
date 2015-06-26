package com.example.juan.eduquer;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by jjortiz on 25/06/15.
 */
public class Webview extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView webView=(WebView)findViewById(R.id.webView);
        Bundle extras= getIntent().getExtras();
        String link= extras.getString("link");
        webView.loadUrl(link);
    }
}
