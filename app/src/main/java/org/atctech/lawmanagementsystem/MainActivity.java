package org.atctech.lawmanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    public NoZoomControllWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if (!isNetworkAvailable(getApplicationContext()))
//        {
//            AlertDialog.Builder alert = new AlertDialog.Builder(this);
//            alert.setCancelable(true);
//            alert.setTitle("Oops!!!");
//            alert.setNeutralButton("Okay!", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            alert.setMessage("You  are not connected to Internet right now! Please check your internet connection");
//            alert.show();
//        }

        ShowWebView();

    }
    public void ShowWebView()
    {

        webView = new NoZoomControllWebView(this);
        webView =  findViewById(R.id.webView1);
        webView.setWebViewClient(new CustomClient());
        webView.getSettings().setJavaScriptEnabled(true);

        WebSettings webSettings = webView.getSettings();

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(false);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        String url = getString(R.string.site_url);
        webView.loadUrl(url);


    }

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class CustomClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {



        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getScheme().equals(getString(R.string.site_url))) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    Activity host = (AppCompatActivity) view.getContext();
                    host.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
//
                    return false;
                }

            }
            return false;
        }


    }

    public void onBackPressed() {
        if (webView.isFocused() && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.action_About:
                startActivity(new Intent(getApplicationContext(),
                        org.atctech.lawmanagementsystem.PreferenceActivity.class));
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
