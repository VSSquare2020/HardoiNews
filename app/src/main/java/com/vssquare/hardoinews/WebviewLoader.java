package com.vssquare.hardoinews;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewLoader {
    private WebView webView;

    public WebviewLoader(WebView webView) {
        this.webView = webView;
    }

    public void setWebSettings(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);
    }

    public void setLoadDataWithBaseUrl(String data){
        webView.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
    }

    public void setLoadUrl(String path){
        webView.loadUrl(path);
    }

}
