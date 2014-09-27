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
    private static final String AUTHORIZE_URL = "";

    private static OAuthProvider mProvider;
    private static OAuthConsumer mConsumer;

    public static void initialize() throws Exception {
        mConsumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);

        // Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
        TembooSession session = new TembooSession("pokedough", "KhanAcademy-LearnAnything", "7f80e64b41284e74975efbf01f058d37");
        InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(session);
        InitializeOAuth.InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
        initializeOAuthInputs.set_ConsumerKey(CONSUMER_KEY);
        initializeOAuthInputs.set_ConsumerSecret(CONSUMER_SECRET);
        InitializeOAuth.InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);
        Log.d(TAG, initializeOAuthResults.get_AuthorizationURL());
    }
}
