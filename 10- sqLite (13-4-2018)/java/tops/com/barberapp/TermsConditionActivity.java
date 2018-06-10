package tops.com.barberapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_terms_condition)
public class TermsConditionActivity extends AppCompatActivity {

    @ViewById
    WebView webView;
    @ViewById
    ProgressBar progressBar;

    String url="https://www.google.co.in/?gfe_rd=cr&dcr=0&ei=I_nNWt6nNeKcX56goKAE";

    @AfterViews
    void onCreate() {
        //super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_terms_condition);

       // progressBar=findViewById(R.id.progressBar);
       // webView=findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
    }

    class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
