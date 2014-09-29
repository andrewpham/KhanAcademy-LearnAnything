package com.andrewpham.android.khanacademy_learnanything;

import android.util.Log;

import com.temboo.Library.KhanAcademy.OAuth.FinalizeOAuth;
import com.temboo.Library.KhanAcademy.OAuth.InitializeOAuth;
import com.temboo.core.TembooSession;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Encoder;
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
    private static final String CALLBACK_URL = "http%3A%2F%2Fwww.khanacademy.org%2Fapi%2Fauth%2Fdefault_callback";

    private static TembooSession mSession;
    private static OAuthProvider mProvider;
    private static OAuthConsumer mConsumer;
    private static String mCallbackId;
    private static String mAuthorizationUrl;

    private static Base64 base64 = new Base64();

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

    public static String authenticate(String token, String secret, String verifier) throws Exception {
        mSession = new TembooSession("pokedough", "KhanAcademy-LearnAnything", "7f80e64b41284e74975efbf01f058d37");
        FinalizeOAuth finalizeOAuthChoreo = new FinalizeOAuth(mSession);
        FinalizeOAuth.FinalizeOAuthInputSet finalizeOAuthInputs = finalizeOAuthChoreo.newInputSet();
        finalizeOAuthInputs.set_ConsumerKey(CONSUMER_KEY);
        finalizeOAuthInputs.set_ConsumerSecret(CONSUMER_SECRET);


        finalizeOAuthInputs.set_CallbackID(mCallbackId);

        Log.d(TAG, mCallbackId);

        FinalizeOAuth.FinalizeOAuthResultSet finalizeOAuthResults = finalizeOAuthChoreo.execute(finalizeOAuthInputs);

        Log.d(TAG, finalizeOAuthResults.get_OAuthToken());
        Log.d(TAG, finalizeOAuthResults.get_OAuthTokenSecret());
//
//        mConsumer.setTokenWithSecret(finalizeOAuthResults.get_OAuthToken(), finalizeOAuthResults.get_OAuthTokenSecret());

//        mConsumer.set
//        URL url = new URL(ACCESS_URL);
//        HttpURLConnection request = (HttpURLConnection) url.openConnection();
//        mConsumer.sign(request);
//        request.

        long nonce = System.currentTimeMillis();
        long timestamp = nonce / 1000L;

        String url = ACCESS_URL;

//        String params = "oauth_callback=" + CALLBACK_URL;
        String params = "oauth_consumer_key=" + CONSUMER_KEY;
        params += "&oauth_nonce=" + Long.toString(nonce);
        params += "&oauth_signature_method=" + "HMAC-SHA1";
        params += "&oauth_timestamp=" + Long.toString(timestamp);
        params += "&oauth_token=" + token;
        params += "&oauth_verifier=" + verifier;
        params += "&oauth_version=" + "1.0";

        String baseString = "GET" + "&" + encode(ACCESS_URL) + "&" + encode(params);

        Log.d(TAG, baseString);
        Log.d(TAG, generateSignature(baseString, CONSUMER_SECRET, secret));
        Log.d(TAG, getSignature(baseString));

        url += "?" + params + "&oauth_signature=" + getSignature(baseString);

        return url;
    }

    private static String encode(String value) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(encoded.length());
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb.append("%2A");
            } else if (focus == '+') {
                sb.append("%20");
            } else if (focus == '%' && i + 1 < encoded.length()
                    && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(focus);
            }
        }
        return sb.toString();
    }

    private static String getSignature(String baseString)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {

        byte[] keyBytes = (CONSUMER_SECRET + "&").getBytes("UTF-8");

        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);

        // encode it, base64 it, change it to string and return.
        return new String(base64.encode(mac.doFinal(baseString.toString().getBytes(
                "UTF-8"))), "UTF-8").trim();
    }

    private static String generateSignature(String signatureBaseStr, String oAuthConsumerSecret, String oAuthTokenSecret) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec;
            if (null == oAuthTokenSecret) {
                String signingKey = encode(oAuthConsumerSecret) + '&';
                spec = new SecretKeySpec(signingKey.getBytes(), "HmacSHA1");
            } else {
                String signingKey = encode(oAuthConsumerSecret) + '&' + encode(oAuthTokenSecret);
                spec = new SecretKeySpec(signingKey.getBytes(), "HmacSHA1");
            }
            mac.init(spec);
            byteHMAC = mac.doFinal(signatureBaseStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(byteHMAC);
    }

}
