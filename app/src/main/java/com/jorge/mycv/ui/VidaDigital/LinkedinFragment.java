package com.jorge.mycv.ui.VidaDigital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jorge.mycv.MainActivity;
import com.jorge.mycv.R;


public class LinkedinFragment extends Fragment {

    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.hideFloatingActionButton();

        final View root = inflater.inflate(R.layout.fragment_linkedin, container, false);
        webView = (WebView) root.findViewById(R.id.webview_linkedin);
        webView.loadUrl("https://www.linkedin.com/in/jorge-s%C3%A1nchez-medina-bb7b7371/");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return root;
    }
}
