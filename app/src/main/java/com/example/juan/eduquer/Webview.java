package com.example.juan.eduquer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jjortiz on 25/06/15.
 */
public class Webview extends Activity{
    public boolean stop=false;
    public int seconds=0;
    Intent intent=new Intent();
    Timer timer=new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView webView=(WebView)findViewById(R.id.webView);
        Bundle extras= getIntent().getExtras();
        String link= extras.getString("link");
        webView.loadUrl(link);

        timer.schedule(new Time(),0,1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        int result=seconds;
        timer.cancel();
        intent.putExtra("seconds",result);
        setResult(7);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer.schedule(new Time(), 0, 1000);
    }

    class Time extends TimerTask{
        @Override
        public void run() {
            seconds++;
        }
    }

}
