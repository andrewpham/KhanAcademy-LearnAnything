package com.andrewpham.android.khanacademy_learnanything;

import android.util.Log;

import com.temboo.Library.KhanAcademy.OAuth.InitializeOAuth;
import com.temboo.core.TembooSession;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

/**
 * Created by andrewpham on 9/26/14.
 */
public class OAuthClient {

    public static final String TAG = "OAuthClient";

    private static final String CONSUMER_KEY = "MSEDRueRnLjvzZe7";
    private static final String CONSUMER_SECRET = "gpkQW95dCDz94ebs";

    private static final String REQUEST_URL = "http://www.khanacademy.org/api/auth/request_token";
    private static final String ACCESS_URL = "http://www.khanacademy.org/api/auth/access_token";
    private static final String CALLBACK_URL = "http://www.khanacademy.org/api/auth/default_callback";

    private static TembooSession mSession;
    private static OAuthProvider mProvider;
    private static OAuthConsumer mConsumer;
    private static String mCallbackId;
    private static String mAuthorizationUrl;

    public static String initialize() throws Exception {
        mConsumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);

        // Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
        mSession = new TembooSession("pokedough", "KhanAcademy-LearnAnything", "7f80e64b41284e74975efbf01f058d37");
        InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(mSession);
        InitializeOAuth.InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
        initializeOAuthInputs.set_ConsumerKey(CONSUMER_KEY);
        initializeOAuthInputs.set_ConsumerSecret(CONSUMER_SECRET);
        InitializeOAuth.InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);

        String authorizationUrl = initializeOAuthResults.get_AuthorizationURL();
        mAuthorizationUrl = authorizationUrl;
        Log.d(TAG, authorizationUrl);
        mCallbackId = initializeOAuthResults.get_CallbackID();

        return authorizationUrl;

//        String callbackId = initializeOAuthResults.get_CallbackID();
//
//        Log.d(TAG, callbackId);
    }

    public static void authenticate() throws Exception {
//        FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(mSession);
//        FinalizeOAuth.FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
//        finalizeOAuthInputs.set_ConsumerKey(CONSUMER_KEY);
//        finalizeOAuthInputs.set_ConsumerSecret(CONSUMER_SECRET);
//
//
//        finalizeOAuthInputs.set_CallbackID(mCallbackId);
//
//        Log.d(TAG, mCallbackId);
//
//        FinalizeOAuth.FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);
//
//        Log.d(TAG, finalizeOAuthResults.get_OAuthToken());
//        Log.d(TAG, finalizeOAuthResults.get_OAuthTokenSecret());
//
//        mConsumer.setTokenWithSecret(finalizeOAuthResults.get_OAuthToken(), finalizeOAuthResults.get_OAuthTokenSecret());

//        mConsumer.set
//        URL url = new URL(ACCESS_URL);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        mConsumer.sign(request);
//        request.

//        mProvider = new CommonsHttpOAuthProvider(REQUEST_URL, ACCESS_URL, mAuthorizationUrl);
//        String url = mProvider.retrieveRequestToken(mConsumer, OAuth.OUT_OF_BAND);
//        Log.d(TAG, url);
    }
}
