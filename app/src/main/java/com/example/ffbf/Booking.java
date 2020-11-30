package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Booking extends AppCompatActivity {

   private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        wv = findViewById(R.id.webVew);
        wv.setWebViewClient(new WebViewClient());
        //set URL link
        wv.loadUrl("https://www.opentable.com/s/?covers=2&dateTime=2019-02-25%2019%3A00&metroId=72&regionIds=5316&pinnedRids%5B0%5D=87967&enableSimpleCuisines=true&includeTicketedAvailability=true&pageType=0 ");
       //enable JavaScript
        WebSettings set = wv.getSettings();
        set.setJavaScriptEnabled(true);
    }

    // on pressing back, web view to navigate to previous page, not previous activity
 @Override
    public void onBackPressed() {
       if(wv.canGoBack()){
           wv.goBack();
       }
       else {
           super.onBackPressed();
       }
    }
}