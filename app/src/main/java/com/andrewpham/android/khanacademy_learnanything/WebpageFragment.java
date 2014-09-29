package com.andrewpham.android.khanacademy_learnanything;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewpham on 9/27/14.
 */
public class WebpageFragment extends Fragment {
    public static final String TAG = "WebpageFragment";

    private String mUrl;
    private WebView mWebView;
    private String token;
    private String secret;
    private String verifier;
    private String accessUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mUrl = getActivity().getIntent().getData().toString();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_webpage, parent, false);

        if (NavUtils.getParentActivityName(getActivity()) != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setMax(100);

        mWebView = (WebView) v.findViewById(R.id.webView);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://pokedough.temboolive.com/callback/")) {
                    URL aURL = null;
                    try {
                        aURL = new URL(url);
                        Map<String, String> queryMap = getQueryMap(aURL.getQuery());
                        token = queryMap.get("oauth_token");
                        secret = queryMap.get("oauth_secret");
                        verifier = queryMap.get("oauth_verifier");
                        new AuthorizeTask().execute();
                        view.loadUrl(accessUrl);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(progress);
                }
            }
        });

        mWebView.loadUrl(mUrl);

        return v;
    }

    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    private class AuthorizeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                accessUrl = OAuthClient.authenticate(token, secret, verifier);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
